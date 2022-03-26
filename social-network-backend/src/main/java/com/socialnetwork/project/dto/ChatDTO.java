package com.socialnetwork.project.dto;

import com.socialnetwork.project.entity.Message;
import com.socialnetwork.project.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChatDTO {
    private String id;
    private String name;
    private User user;
    private List<User> users;
    private List<Message> messages;
}
