package com.socialnetwork.project.dto;

import com.socialnetwork.project.entity.Chat;
import com.socialnetwork.project.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SentMessageDTO {
    private Long chatId;
    private String text;
}
