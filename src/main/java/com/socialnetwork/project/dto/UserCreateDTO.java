package com.socialnetwork.project.dto;

import com.socialnetwork.project.entity.enums.Sex;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserCreateDTO {

    @NotBlank(message = "Name should not be empty")
    @Size(max = 64, message = "Name should be less then 64")
    private String name;

    @NotBlank(message = "Surname should not be empty")
    @Size(max = 64, message = "Surname should be less then 64")
    private String surname;

    @NotBlank(message = "Username should not be empty")
    @Size(max = 64, message = "Username should be less then 64")
    private String username;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email should not be empty")
    private String email;

    @NotBlank(message = "Phone cannot be empty")
    @Size(max = 12, message = "Phone must be less then 12")
    private String phone;

    @NotNull
    private Sex sex;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 8, max = 64, message = "Password should be between 8 and 64")
    private String password;
}
