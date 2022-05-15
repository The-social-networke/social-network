package com.socialnetwork.project.dto;

import com.socialnetwork.project.entity.enums.Sex;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserCreateDTO {

    private String name;

    private String surname;

    private String username;

    private String email;

    private String phone;

    private Sex sex;

    private String password;
}
