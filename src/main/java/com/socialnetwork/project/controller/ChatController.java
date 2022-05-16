package com.socialnetwork.project.controller;

import com.socialnetwork.project.annotation.CurrentUser;
import com.socialnetwork.project.dto.*;
import com.socialnetwork.project.entity.*;
import com.socialnetwork.project.mapper.ChatMapper;
import com.socialnetwork.project.mapper.MessageMapper;
import com.socialnetwork.project.security.UserSecurity;
import com.socialnetwork.project.service.ChatService;
import com.socialnetwork.project.service.MessageService;
import com.socialnetwork.project.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("chats")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ChatController {

    private final UserService userService;

    private final ChatService chatService;
    private final ChatMapper chatMapper;

    private final MessageService messageService;
    private final MessageMapper messageMapper;

    @GetMapping("/{id}")
    public ChatDTO getChatById(
            @PathVariable("id") Long chatId,
            @CurrentUser UserSecurity userSecurity
    ) {
        return chatService.getChatById(chatId, userSecurity.getId());
    }

    @GetMapping
    public List<ChatListDTO> getAllChats(
            @CurrentUser UserSecurity userSecurity
    ) {
        return chatService.getAllChatsByUserId(userSecurity.getId());
    }

    @PostMapping("/get-chat")
    public ChatDTO getChatRoomByUsersOrElseCreate(
            @Valid @RequestBody ChatCreateDTO dto,
            @CurrentUser UserSecurity userSecurity
    ) {
        dto.setCurrentUserId(userSecurity.getId());
        return chatService.getChatByUserOrElseCreate(dto);
    }

    @PostMapping("/get-system-chat")
    public ChatDTO getSystemChatRoomByUserOrElseCreate(
            @CurrentUser UserSecurity userSecurity
    ) {
        return chatService.getSystemChatByUserIdOrElseCreate(userSecurity.getId());
    }

    @PostMapping
    public ChatDTO createChat(
            @Valid @RequestBody ChatCreateDTO dto,
            @CurrentUser UserSecurity userSecurity
    ) {
        dto.setCurrentUserId(userSecurity.getId());
        return chatService.createChat(dto);
    }

    @DeleteMapping
    public boolean deleteChat(
            @Valid @RequestBody ChatDeleteDTO dto,
            @CurrentUser UserSecurity userSecurity
    ) {
        dto.setUserId(userSecurity.getId());
        return chatService.deleteChat(dto);
    }
}
