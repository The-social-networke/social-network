package com.socialnetwork.project.mapper;

import com.socialnetwork.project.dto.ChatDTO;
import com.socialnetwork.project.dto.ChatListDTO;
import com.socialnetwork.project.dto.CreateChatDTO;
import com.socialnetwork.project.entity.Chat;
import com.socialnetwork.project.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(uses = {UserMapper.class, MessageMapper.class})
public interface ChatMapper {

    @Named("usersIdToUsers")
    default Set<User> usersIdToUsers(List<Long> usersId) {
        return usersId.stream().
                map(id -> {
                    User user = new User();
                    user.setId(id);
                    return user;
                })
                .collect(Collectors.toSet());
    }

    @Mapping(source = "usersId", target = "users", qualifiedByName = "usersIdToUsers")
    Chat toChat(CreateChatDTO chatDTO);

    ChatDTO toChatDTO(Chat chat);

    List<ChatListDTO> toChatListDTO(List<Chat> chats);
}
