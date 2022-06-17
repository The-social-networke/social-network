package com.socialnetwork.project.dto;

import com.socialnetwork.project.entity.enums.Sex;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ProfileDTO {

    private Long id;

    private String avatar;

    private String name;

    private String surname;

    private String username;

    private String email;

    private String phone;

    private Sex sex;

    private String background;

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
