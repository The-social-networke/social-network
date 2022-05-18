package com.socialnetwork.project.dto;

import com.socialnetwork.project.entity.Chat;
import com.socialnetwork.project.entity.User;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

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

    private String photo;

    private Long forwardId;

    private boolean isUpdated;

    private LocalDateTime sentAt;

    private Set<Long> readMessages;

    private Set<Long> likedMessages;
}
