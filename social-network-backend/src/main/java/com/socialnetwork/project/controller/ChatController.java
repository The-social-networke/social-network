package com.socialnetwork.project.controller;

import com.socialnetwork.project.annotation.CurrentUser;
import com.socialnetwork.project.dto.*;
import com.socialnetwork.project.entity.*;
import com.socialnetwork.project.entity.enums.ChatRole;
import com.socialnetwork.project.mapper.ChatMapper;
import com.socialnetwork.project.mapper.MessageMapper;
import com.socialnetwork.project.security.CustomUserDetails;
import com.socialnetwork.project.service.ChatService;
import com.socialnetwork.project.service.MessageService;
import com.socialnetwork.project.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
@RequestMapping("chats")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ChatController {

    private final UserService userService;
    private final ChatService chatService;
    private final MessageService messageService;

    private final ChatMapper chatMapper;
    private final MessageMapper messageMapper;

    @GetMapping
    public ResponseEntity<List<ChatListDTO>> chats() {
        return ResponseEntity.ok(chatMapper.toChatListDTO(chatService.getAll()));
    }

    @PostMapping
    public ResponseEntity<ChatDTO> createChat(@CurrentUser CustomUserDetails userDetails,
                                           @RequestBody CreateChatDTO chatDTO) {
        /*User owner = userService.findByEmail(userDetails.getEmail());
        Chat chat = chatMapper.toChat(chatDTO);
        chat.getUsers().add(ChatUser.builder().user(owner).role(ChatRole.MEMBER).build());
        chat = chatService.create(chat);*/
        User user1 = userService.findByEmail(userDetails.getEmail());
        User user2 = userService.readById(7L);
        Chat chat = new Chat();
        chat.setName("Наш чат6");
        chat = chatService.create(chat);
        Set<ChatUser> list = new HashSet<>();
        list.add(ChatUser.builder()
                        .id(ChatUserPK.builder().chatId(chat.getId()).userId(user1.getId()).build())
                .chat(chat)
                .user(user1)
                .role(ChatRole.MEMBER)
                .build());
        list.add(ChatUser.builder()
                .id(ChatUserPK.builder().chatId(chat.getId()).userId(user2.getId()).build())
                .chat(chat)
                .user(user2)
                .role(ChatRole.MEMBER)
                .build());
        chat.setUsers(list);
        chat = chatService.update(chat);
        return ResponseEntity.ok(chatMapper.toChatDTO(chat));
        //return null;
    }

    @GetMapping("{id}")
    public ResponseEntity<ChatDTO> chat(@PathVariable("id") Long id) {
        return ResponseEntity.ok(chatMapper.toChatDTO(chatService.readById(id)));
    }

    @PostMapping("{id}")
    public ResponseEntity<MessageDTO> sentMessage(@CurrentUser CustomUserDetails userDetails,
                                                  @PathVariable("id") Long id,
                                                  @RequestBody SentMessageDTO messageDTO) {
        User user = userService.findByEmail(userDetails.getEmail());
        Chat chat = chatService.readById(id);
        Message message = messageMapper.toMessage(messageDTO);
        message.setUser(user);
        message.setChat(chat);
        message = messageService.create(message);
        return ResponseEntity.ok(messageMapper.toMessageDTO(message));
    }
}
