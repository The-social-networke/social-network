package com.socialnetwork.project.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class AuthenticationRequestDTO {

    private String email;

    private String password;
}
