package com.socialnetwork.project.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class PostCreateDTO {

    @JsonIgnore
    private Long userId;

    private String text;
}
