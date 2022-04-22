package com.socialnetwork.project.dto;

import com.socialnetwork.project.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateChatDTO {
    private String name;
    private List<Long> usersId;
}

