package com.socialnetwork.project.service.impl;

import com.socialnetwork.project.dto.*;
import com.socialnetwork.project.entity.Chat;
import com.socialnetwork.project.entity.Message;
import com.socialnetwork.project.entity.User;
import com.socialnetwork.project.entity.enums.MessageStatus;
import com.socialnetwork.project.exception.ChatException;
import com.socialnetwork.project.mapper.MessageMapper;
import com.socialnetwork.project.repository.ChatRepository;
import com.socialnetwork.project.repository.MessageRepository;
import com.socialnetwork.project.repository.UserRepository;
import com.socialnetwork.project.service.ChatService;
import com.socialnetwork.project.service.MessageService;
import com.socialnetwork.project.util.ErrorCodeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ChatServiceImpl implements ChatService {

    private static final String USER_SOCKET_NOTIFICATION = "/ws-users/";
    private static final String CHAT_SOCKET_NOTIFICATION = "/ws-chats/messages/";

    @Value("${app.system-user-email}")
    private String systemUserEmail;

    private final ChatRepository chatRepository;

    private final MessageRepository messageRepository;
    private final MessageService messageService;
    private final MessageMapper messageMapper;

    private final UserRepository userRepository;

    private final SimpMessagingTemplate template;

    @Override
    public Chat getChatById(Long chatId, Long userId) {
        log.info("Find chat with chatId = {} and userId = {}", chatId, userId);

        Chat chat = getChatOrElseThrow(chatId);
        checkIfUserMemberOfChat(chat, userId);

        return chat;
    }

    @Override
    @Transactional
    public Chat getChatByUserOrElseCreate(ChatCreateDTO dto) {
        log.info("getChatRoomByUsersOrElseCreate by users with currentUserId = {}, and userId = {}", dto.getCurrentUserId(), dto.getUserId());

        Optional<Chat> chat = chatRepository.findChatByUsers(dto.getCurrentUserId(), dto.getUserId());
        if(chat.isPresent()) {
            User user = userRepository.findById(dto.getCurrentUserId()).orElseThrow();
            User anotherUser = userRepository.findById(dto.getUserId()).orElseThrow();

            Chat entity = new Chat()
                    .toBuilder()
                    .users(Set.of(user, anotherUser))
                    .build();
            return chatRepository.save(entity);
        }
        return chat.get();
    }

    @Override
    @Transactional
    public Chat getSystemChatByUserIdOrElseCreate(Long userId) {
        log.info("Find system chat room by users with userId = {}", userId);

        User systemUser = userRepository.findByEmail(systemUserEmail).orElse(new User());
        Optional<Chat> chat = chatRepository.findChatByUsers(userId, systemUser.getId());
        if(!chat.isPresent()) {
            Chat newChat = new Chat()
                    .toBuilder()
                    .users(Set.of(
                            userRepository.findById(userId).orElseThrow(),
                            systemUser
                    ))
                    .build();
            return chatRepository.save(newChat);
        }
        return chat.get();
    }

    @Override
    public List<ChatListDTO> getAllChatsByUserId(Long userId) {
        log.info("Get all chats by userId = {}", userId);
        var result = chatRepository.getAllChatsByUserId(userId);
        result = result.stream()
                .map(u -> {
                    User anotherUser = userRepository.findById(u.getAnotherUserId()).orElseThrow();
                    return u.toBuilder()
                            .name(anotherUser.getName())
                            .surname(anotherUser.getSurname())
                            .build();
                })
                .collect(Collectors.toList());
        return result;
    }

    @Override
    @Transactional
    public Chat createChat(ChatCreateDTO dto) {
        log.info("Create chat");

        if(chatRepository.existsChatByUsers(dto.getCurrentUserId(), dto.getUserId())) {
            throw new ChatException(ErrorCodeException.CHAT_WITH_THESE_USERS_ALREADY_EXISTS);
        }
        Chat chat = new Chat()
                .toBuilder()
                .users(Set.of(
                        userRepository.findById(dto.getCurrentUserId()).orElseThrow(),
                        userRepository.findById(dto.getUserId()).orElseThrow()
                ))
                .build();

        return chatRepository.save(chat);
    }

    @Override
    @Transactional
    public boolean deleteChat(ChatDeleteDTO dto) {
        log.info("Delete chat");

        Chat chat = getChatOrElseThrow(dto.getChatId());
        checkIfUserMemberOfChat(chat, dto.getUserId());

        chatRepository.deleteById(chat.getId());

        return true;
    }

    @Override
    @Transactional
    public MessageDTO sendMessage(MessageCreateDTO dto) {
        log.info("Send message");

        Chat chat = getChatOrElseThrow(dto.getChatId());
        checkIfUserMemberOfChat(chat, dto.getUserId());

        Message sendMessage = messageService.sendMessage(dto);

        User anotherUser = getAnotherUserIdFromChat(chat, dto.getUserId());
        template.convertAndSend(USER_SOCKET_NOTIFICATION + anotherUser.getId(), convertToChatMessageStatusDTO(chat.getId(), sendMessage, MessageStatus.SENT));
        template.convertAndSend(CHAT_SOCKET_NOTIFICATION + dto.getChatId(), convertToChatMessageStatusDTO(chat.getId(), sendMessage, MessageStatus.SENT));

        return messageMapper.toMessageDTO(sendMessage);
    }

    @Override
    @Transactional
    public MessageDTO updateMessage(MessageUpdateDTO dto) {
        log.info("Update message");

        Chat chat = chatRepository.findChatByMessageId(dto.getMessageId())
                .orElseThrow(() -> new ChatException(ErrorCodeException.CHAT_NOT_FOUND));
        checkIfUserMemberOfChat(chat, dto.getUserId());

        Message updateMessage = messageService.updateMessage(dto);
        if(chatRepository.isLastMessageInChat(chat.getId(), updateMessage.getId())) {
            User anotherUser = getAnotherUserIdFromChat(chat, dto.getUserId());
            template.convertAndSend(USER_SOCKET_NOTIFICATION + anotherUser.getId(), convertToChatMessageStatusDTO(chat.getId(), updateMessage, MessageStatus.UPDATED));
        }
        template.convertAndSend(CHAT_SOCKET_NOTIFICATION + chat.getId(), convertToChatMessageStatusDTO(chat.getId(), updateMessage, MessageStatus.UPDATED));

        return messageMapper.toMessageDTO(updateMessage);
    }

    @Override
    @Transactional
    public MessageDTO deleteMessage(MessageDeleteDTO dto) {
        log.info("Delete message");

        Chat chat = chatRepository.findChatByMessageId(dto.getMessageId())
                .orElseThrow(() -> new ChatException(ErrorCodeException.CHAT_NOT_FOUND));
        checkIfUserMemberOfChat(chat, dto.getUserId());

        boolean isLastMessage = chatRepository.isLastMessageInChat(chat.getId(), dto.getMessageId());

        Message deletedMessage = messageService.deleteMessage(dto);

        if(isLastMessage) {
            var lastMessage = messageRepository.findLastMessageInChat(chat.getId());
            var chatMessageDTO = convertToChatMessageStatusDTO(chat.getId(), lastMessage.orElse(null), MessageStatus.DELETED);
            var anotherUser = getAnotherUserIdFromChat(chat, dto.getUserId());
            template.convertAndSend(USER_SOCKET_NOTIFICATION + anotherUser.getId(), chatMessageDTO);
        }
        template.convertAndSend(CHAT_SOCKET_NOTIFICATION + chat.getId(), deletedMessage);

        return messageMapper.toMessageDTO(deletedMessage);
    }

    @Override
    @Transactional
    public MessageDTO readMessage(MessageReadDTO dto) {
        log.info("Read message");

        Chat chat = chatRepository.findChatByMessageId(dto.getMessageId())
                .orElseThrow(() -> new ChatException(ErrorCodeException.CHAT_NOT_FOUND));
        checkIfUserMemberOfChat(chat, dto.getUserId());

        Message readMessage = messageService.readMessage(dto);

        //TODO: to user notification

        template.convertAndSend(CHAT_SOCKET_NOTIFICATION + chat.getId(), convertToChatMessageStatusDTO(chat.getId(), readMessage, MessageStatus.UPDATED));

        return messageMapper.toMessageDTO(readMessage);
    }

    @Override
    @Transactional
    public MessageDTO toggleLikeMessage(MessageLikeDTO dto) {
        log.info("Like message {}", dto.getIsLike());

        Chat chat = chatRepository.findChatByMessageId(dto.getMessageId())
                .orElseThrow(() -> new ChatException(ErrorCodeException.CHAT_NOT_FOUND));
        checkIfUserMemberOfChat(chat, dto.getUserId());

        Message changedMessage = messageService.toggleLikeMessage(dto);

        template.convertAndSend(CHAT_SOCKET_NOTIFICATION + chat.getId(), convertToChatMessageStatusDTO(chat.getId(), changedMessage, MessageStatus.UPDATED));

        return messageMapper.toMessageDTO(changedMessage);
    }


    private Chat getChatOrElseThrow(Long chatId) {
        return chatRepository.findById(chatId)
                .orElseThrow(() -> new ChatException(ErrorCodeException.CHAT_NOT_FOUND));
    }

    private static void checkIfUserMemberOfChat(Chat chat, Long userId) throws ChatException {
        boolean isMemberOfChat = chat.getUsers()
                .stream()
                .anyMatch(u -> u.getId().equals(userId));
        if(!isMemberOfChat) {
            throw new ChatException(ErrorCodeException.NOT_MEMBER_OF_CHAT);
        }
    }

    private User getAnotherUserIdFromChat(Chat chat, Long userId) throws ChatException {
        return chat.getUsers()
                .stream()
                .filter(u -> !u.getId().equals(userId))
                .findFirst()
                .orElseThrow();
    }

    private ChatMessageDTO convertToChatMessageStatusDTO(Long chatId, Message message, MessageStatus messageStatus) {
        return new ChatMessageDTO()
                .toBuilder()
                .chatId(chatId)
                .messageId(message == null ? null : message.getId())
                .userId(message == null ? null : message.getUser().getId())
                .text(message == null ? null : message.getText())
                .sentAt(message == null ? null : message.getSentAt())
                .messageStatus(messageStatus)
                .build();
    }
}
