package com.socialnetwork.project.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ProfileUpdateDTO {

    @JsonIgnore
    private Long userId;

    private String instagram;

    private String telegram;

    private String facebook;

    private String discord;

    private String twitter;

    private String linkedIn;

    private String skype;

    private LocalDate birthday;

    private String description;
}
