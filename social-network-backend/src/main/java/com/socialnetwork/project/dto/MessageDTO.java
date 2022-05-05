package com.socialnetwork.project.dto;

import com.socialnetwork.project.entity.Chat;
import com.socialnetwork.project.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MessageDTO {
    private Long id;
    private Long userId;
    private Long chatId;
    private String text;
    private boolean isUpdated;
    private LocalDateTime sentAt;
}
