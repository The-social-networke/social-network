package com.socialnetwork.project.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.socialnetwork.project.entity.enums.Sex;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserUpdateDTO {

    @JsonIgnore
    private Long userId;

    @NotBlank(message = "Name cannot be empty")
    @Size(max = 64, message = "Name must be between 8 and 64")
    private String name;

    @NotBlank(message = "Surname cannot be empty")
    @Size(max = 64, message = "Surname must be up to 64")
    private String surname;

    @NotBlank(message = "Username cannot be empty")
    @Size(max = 64, message = "Username must be up to 64")
    private String username;

    @Email(message = "Email does not match format")
    @NotBlank(message = "Email cannot be empty")
    private String email;

    @NotBlank(message = "Phone cannot be empty")
    @Size(max = 64, message = "Phone must be up to 64")
    private String phone;

    @NotNull
    private Sex sex;

    /*@NotBlank(message = "Password cannot be empty")
    @Size(min = 8, max = 64, message = "Password must be between 8 and 64")
    private String password;

    @NotBlank(message = "New password cannot be empty")
    @Size(min = 8, max = 64, message = "New password must be between 8 and 64")
    private String newPassword;*/

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
