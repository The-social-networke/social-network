package com.socialnetwork.project.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.socialnetwork.project.entity.Chat;
import com.socialnetwork.project.entity.User;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class MessageCreateDTO {

    @JsonIgnore
    private Long userId;

    @NotNull
    @Min(value = 1, message = "ChatId cannot be less than one")
    private Long chatId;

    @NotBlank(message = "Message cannot be empty")
    private String text;
}
