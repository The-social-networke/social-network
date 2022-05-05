package com.socialnetwork.project.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatDeleteDTO {

    private Long chatId;

    @JsonIgnore
    private Long userId;
}
