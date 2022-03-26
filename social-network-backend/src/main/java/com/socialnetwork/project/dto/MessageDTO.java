package com.socialnetwork.project.dto;

import com.socialnetwork.project.entity.Chat;
import com.socialnetwork.project.entity.User;

public class MessageDTO {
    private Long id;
    private Long chatId;
    private Long userId;
    private String text;
    private boolean isUpdated;
}
