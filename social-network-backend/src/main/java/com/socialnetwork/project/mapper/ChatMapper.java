package com.socialnetwork.project.mapper;

import com.socialnetwork.project.dto.ChatDTO;
import com.socialnetwork.project.dto.ChatListDTO;
import com.socialnetwork.project.dto.CreateChatDTO;
import com.socialnetwork.project.entity.Chat;
import com.socialnetwork.project.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper
public interface ChatMapper {

    /*default Set<User> authorsIdToAuthors(List<Long> list) {
        return list.stream().
                map(aLong -> {
                    Author author = new Author();
                    author.setId(aLong);
                    return author;
                })
                .collect(Collectors.toSet());
    }

    @Mapping(source = "usersId", target = "users", qualifiedByName = "usersIdToUsers")
    Chat toChat(CreateChatDTO chatDTO);

    /*ChatDTO toChatDTO(Chat chat);
    Chat toChat(ChatDTO chatDTO);
    List<ChatListDTO> toChatListDTO(List<Chat> chats);*/
}
