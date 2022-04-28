package com.socialnetwork.project.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
public class UserDTO {

    private Long id;

    private String name;

    private String surname;

    private String username;

    private String email;

    private String phone;

    private String password;
}
