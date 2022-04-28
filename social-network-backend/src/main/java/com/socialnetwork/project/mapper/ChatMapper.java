package com.socialnetwork.project.mapper;

import com.socialnetwork.project.dto.ChatDTO;
import com.socialnetwork.project.dto.ChatListDTO;
import com.socialnetwork.project.dto.CreateChatDTO;
import com.socialnetwork.project.entity.Chat;
import com.socialnetwork.project.entity.ChatUser;
import com.socialnetwork.project.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface ChatMapper {

    @Named("usersIdToChatUsers")
    default Set<ChatUser> usersIdToChatUsers(List<Long> list) {
        return list.stream().
                map(id ->
                {
                    User user = new User();
                    user.setId(id);
                    return ChatUser.builder().user(user).build();
                })
                .collect(Collectors.toSet());
    }

    @Mapping(source = "usersId", target = "users", qualifiedByName = "usersIdToChatUsers")
    Chat toChat(CreateChatDTO chatDTO);

    @Named("chatUsersToUsers")
    default Set<User> chatUsersToUsers(Set<ChatUser> users) {
        return users.stream().
                map(ChatUser::getUser)
                .collect(Collectors.toSet());
    }
    @Mapping(source = "users", target = "users", qualifiedByName = "chatUsersToUsers")
    ChatDTO toChatDTO(Chat chat);

    List<ChatListDTO> toChatListDTO(List<Chat> chats);
}
