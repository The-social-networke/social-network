package com.socialnetwork.project.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.socialnetwork.project.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChatCreateDTO {

    private Long userId;

    @JsonIgnore
    private Long currentUserId;
}

