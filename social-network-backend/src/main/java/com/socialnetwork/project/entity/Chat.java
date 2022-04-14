package com.socialnetwork.project.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
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
    @OneToMany(mappedBy = "chat", fetch = FetchType.LAZY, targetEntity = ChatUser.class)
    private Set<ChatUser> users = new HashSet<>();

    @OneToMany(mappedBy = "chat", fetch = FetchType.LAZY, targetEntity = Message.class)
    Set<Message> messages = new HashSet<>();

    @CreationTimestamp
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @Column(name = "sent_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
