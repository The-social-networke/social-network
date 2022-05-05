package com.socialnetwork.project.dto;

import com.socialnetwork.project.entity.enums.MessageStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ChatMessageStatusDTO {

    private Long chatId;

    private Long userId;

    private Long messageId;

    private String text;

    private LocalDateTime sentAt;

    private MessageStatus messageStatus;
}
