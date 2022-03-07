package com.socialnetwork.project.entity;

import com.socialnetwork.project.entity.enums.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    private String email;

    private String phone;

    private String password;

    //private Set<Role> roles;

    private boolean enabled;
}
