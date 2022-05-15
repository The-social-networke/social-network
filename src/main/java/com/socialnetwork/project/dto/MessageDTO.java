package com.socialnetwork.project.dto;

import com.socialnetwork.project.entity.Chat;
import com.socialnetwork.project.entity.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class MessageDTO {

    private Long id;

    private Long userId;

    private Long chatId;

    private String text;

    private boolean isUpdated;

    private LocalDateTime sentAt;
}
