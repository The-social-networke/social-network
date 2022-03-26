package com.socialnetwork.project.mapper;

import com.socialnetwork.project.dto.ChatDTO;
import com.socialnetwork.project.dto.ChatListDTO;
import com.socialnetwork.project.dto.CreateChatDTO;
import com.socialnetwork.project.entity.Chat;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ChatMapper {

    Chat toChat(CreateChatDTO chatDTO);

    ChatDTO toChatDTO(Chat chat);
    Chat toChat(ChatDTO chatDTO);
    List<ChatListDTO> toChatListDTO(List<Chat> chats);
}
