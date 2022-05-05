package com.socialnetwork.project.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageUpdateDTO {

    private Long messageId;

    @JsonIgnore
    private Long userId;

    private String text;
}
