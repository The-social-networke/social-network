package com.socialnetwork.project.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageDeleteDTO {

    private Long messageId;

    @JsonIgnore
    private Long userId;
}
