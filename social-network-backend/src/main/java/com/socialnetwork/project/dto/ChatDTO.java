package com.socialnetwork.project.dto;

import com.socialnetwork.project.entity.Message;
import com.socialnetwork.project.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class ChatDTO {
    private String id;
    private String name;
    private Set<UserDTO> users;
    private Set<MessageDTO> messages;
}
