package com.socialnetwork.project.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ChatListDTO {

    private Long chatId;

    private Long anotherUserId;

    private Long userId;

    private String name;

    private String surname;

    private Long messageId;

    private String text;

    private LocalDateTime sentAt;

    private int amountNotReadMessages;
}
