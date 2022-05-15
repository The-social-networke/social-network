package com.socialnetwork.project.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class MessageReadDTO {

    private Long messageId;

    @JsonIgnore
    private Long userId;
}
