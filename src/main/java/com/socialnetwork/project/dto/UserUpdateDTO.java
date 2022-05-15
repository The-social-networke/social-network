package com.socialnetwork.project.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.socialnetwork.project.entity.enums.Sex;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserUpdateDTO {

    @JsonIgnore
    private Long id;

    private String name;

    private String surname;

    private String username;

    private String email;

    private String phone;

    private Sex sex;

    private String password;

    private String newPassword;
}
