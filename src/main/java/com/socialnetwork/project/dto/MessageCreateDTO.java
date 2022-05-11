package com.socialnetwork.project.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.socialnetwork.project.entity.Chat;
import com.socialnetwork.project.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageCreateDTO {

    @JsonIgnore
    private Long userId;

    private Long chatId;

    private String text;
}
