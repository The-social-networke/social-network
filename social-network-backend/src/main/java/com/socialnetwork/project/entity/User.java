package com.socialnetwork.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.socialnetwork.project.entity.enums.Role;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    //TODO: add username

    private String email;

    private String phone;

    private String password;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Set<Role> roles;

    private boolean enabled = true;


    @JsonIgnore
    @OneToMany(mappedBy = "user", targetEntity = Chat.class)
    private List<Chat> owner_chats = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "users", targetEntity = Chat.class)
    private List<Chat> chats = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", targetEntity = Message.class)
    private List<Message> messages = new ArrayList<>();
}


