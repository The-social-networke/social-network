package com.socialnetwork.project.entity;

import com.socialnetwork.project.entity.enums.Role;

import java.util.Set;

public class User {

    private Long id;

    private String name;

    private String surname;

    private String email;

    private String phone;

    private String password;

    private Set<Role> roles;

    private boolean enabled;

    //private Set<Role> roles;
}
