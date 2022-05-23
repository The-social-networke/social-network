package com.socialnetwork.project.service;

import com.socialnetwork.project.dto.*;

import java.util.List;

public interface ChatService {

    ChatDTO getChatById(Long chatId, Long userId);

    ChatDTO getChatByUserOrElseCreate(ChatCreateDTO dto);

    ChatDTO getSystemChatByUserIdOrElseCreate(Long userId);

    List<ChatListDTO> getAllChatsByUserId(Long userId);

    ChatDTO createChat(ChatCreateDTO dto);

    boolean deleteChat(ChatDeleteDTO dto);

    MessageDTO sendMessage(MessageCreateDTO dto);

    MessageDTO updateMessage(MessageUpdateDTO dto);

    MessageDTO deleteMessage(MessageDeleteDTO dto);

    MessageDTO readMessage(MessageReadDTO dto);

    MessageDTO toggleLikeMessage(MessageLikeDTO dto);

    List<UserDTO> searchChats(String search);
}
