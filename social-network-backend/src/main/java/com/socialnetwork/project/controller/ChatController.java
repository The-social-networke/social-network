package com.socialnetwork.project.controller;

import com.socialnetwork.project.annotation.CurrentUser;
import com.socialnetwork.project.dto.ChatDTO;
import com.socialnetwork.project.dto.ChatListDTO;
import com.socialnetwork.project.dto.CreateChatDTO;
import com.socialnetwork.project.dto.SentMessageDTO;
import com.socialnetwork.project.entity.Chat;
import com.socialnetwork.project.entity.Message;
import com.socialnetwork.project.entity.User;
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
        //return ResponseEntity.ok(chatMapper.toChatListDTO(chatService.getAll()));
        return null;
    }

    @PostMapping
    public ResponseEntity<ChatDTO> createChat(/*@RequestBody CreateChatDTO chatDTO*/) {
        //Chat chat = chatMapper.toChat(chatDTO);
        /*User user1 = userService.readById(1L);
        User user2 = userService.readById(2L);
        Chat chat = new Chat();
        chat.setName("Наш чат");
        chat.setUser(user1);
        Set<User> list = new HashSet<>();
        list.add(user1);
        list.add(user2);
        chat.setUsers(list);
        chat = chatService.create(chat);
        return ResponseEntity.ok(chatMapper.toChatDTO(chat));*/
        return null;
    }

    @GetMapping("{id}")
    public ResponseEntity<ChatDTO> chat(@PathVariable("id") Long id) {
        //return ResponseEntity.ok(chatMapper.toChatDTO(chatService.readById(id)));
        return null;
    }

    @PostMapping("{id}")
    public ResponseEntity<Message> sentMessage(@CurrentUser CustomUserDetails userDetails,
                                               @PathVariable("id") Long id,
                                               @RequestBody SentMessageDTO messageDTO) {
        User user = userService.findByEmail(userDetails.getEmail());
        Chat chat = chatService.readById(id);
        Message message = messageMapper.toMessage(messageDTO);
        message.setUser(user);
        message.setChat(chat);
        message = messageService.create(message);
        return ResponseEntity.ok(message);
    }
}
