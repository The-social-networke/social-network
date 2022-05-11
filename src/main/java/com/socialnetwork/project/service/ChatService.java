package com.socialnetwork.project.service;

import com.socialnetwork.project.dto.*;
import com.socialnetwork.project.entity.Chat;
import com.socialnetwork.project.entity.Message;

import java.util.List;

public interface ChatService {

    Chat getChatById(Long chatId, Long userId);
    Chat getChatByUserOrElseCreate(ChatCreateDTO dto);
    Chat getSystemChatByUserIdOrElseCreate(Long userId);
    List<ChatListDTO> getAllChatsByUserId(Long userId);

    Chat createChat(ChatCreateDTO dto);
    boolean deleteChat(ChatDeleteDTO dto);

    MessageDTO sendMessage(MessageCreateDTO dto);
    MessageDTO updateMessage(MessageUpdateDTO dto);
    MessageDTO deleteMessage(MessageDeleteDTO dto);
    MessageDTO readMessage(MessageReadDTO dto);
    MessageDTO toggleLikeMessage(MessageLikeDTO dto);
}
