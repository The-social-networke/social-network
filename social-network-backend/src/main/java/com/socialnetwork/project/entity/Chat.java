package com.socialnetwork.project.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "chats")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "owner_id")
    private User user;

    @NotNull
    @ManyToMany(fetch = FetchType.EAGER, targetEntity = User.class)
            @JoinTable(
                    name = "users_chats",
                    joinColumns = @JoinColumn(name = "chat_id", referencedColumnName = "id"),
                    inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
            )
    List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "chat", targetEntity = Message.class)
    List<Message> messages = new ArrayList<>();
}
