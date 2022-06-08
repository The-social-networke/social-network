package com.socialnetwork.project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ChatListDTO {

    private Long chatId;

    private Long anotherUserId;

    private Long userId;

    private String avatar;

    private String name;

    private String surname;

    private Long messageId;

    private String text;

    private LocalDateTime sentAt;

    @JsonProperty("isRead")
    private boolean isRead;

    @JsonProperty("isUpdated")
    private boolean isUpdated;

    private LocalDateTime createdAt;

    private int amountNotReadMessages;
}
