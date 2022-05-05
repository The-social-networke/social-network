package com.socialnetwork.project.controller;

import com.socialnetwork.project.annotation.CurrentUser;
import com.socialnetwork.project.dto.*;
import com.socialnetwork.project.security.UserSecurity;
import com.socialnetwork.project.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ChatSocketController {

    private final ChatService chatService;

    @PostMapping("/chats/sendMessage")
    public MessageDTO sendMessage(
            @CurrentUser UserSecurity userDetails,
            @RequestBody MessageCreateDTO dto
    ) {
        dto.setUserId(userDetails.getId());
        return chatService.sendMessage(dto);
    }

    @PutMapping("/chats/updateMessage")
    public MessageDTO sendMessage(
            @CurrentUser UserSecurity userDetails,
            @RequestBody MessageUpdateDTO dto
    ) {
        dto.setUserId(userDetails.getId());
        return chatService.updateMessage(dto);
    }

    @DeleteMapping("/chats/deleteMessage")
    public MessageDTO sendMessage(
            @CurrentUser UserSecurity userDetails,
            @RequestBody MessageDeleteDTO dto
    ) {
        dto.setUserId(userDetails.getId());
        return chatService.deleteMessage(dto);
    }

    @PutMapping("/chats/readMessage")
    public MessageDTO readMessage(
            @CurrentUser UserSecurity userDetails,
            @RequestBody MessageReadDTO dto
    ) {
        dto.setUserId(userDetails.getId());
        return chatService.readMessage(dto);
    }

    @PutMapping("/chats/likeMessage")
    public MessageDTO readMessage(
            @CurrentUser UserSecurity userDetails,
            @RequestBody MessageLikeDTO dto
    ) {
        dto.setUserId(userDetails.getId());
        return chatService.toggleLikeMessage(dto);
    }
}
