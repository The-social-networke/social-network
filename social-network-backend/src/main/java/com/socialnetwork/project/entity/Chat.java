package com.socialnetwork.project.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = User.class)
            @JoinTable(
                    name = "users_chats",
                    joinColumns = @JoinColumn(name = "chat_id", referencedColumnName = "id"),
                    inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
            )
    List<User> users;

    @OneToMany(mappedBy = "chat", targetEntity = Message.class)
    List<Message> messages;
}
