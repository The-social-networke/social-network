package com.socialnetwork.project.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(targetEntity = Chat.class)
    @Column(name = "chat_id", nullable = false)
    private Chat chat;

    @NotNull
    @ManyToOne(targetEntity = User.class)
    @Column(name = "user_id", nullable = false)
    private User user;

    private String text;

    @CreationTimestamp
    @Column(name = "sent_at", nullable = false, updatable = false)
    private LocalDateTime sentAt;     //Spring Auditing

    @Column(name = "is_updated", nullable = false, updatable = false)
    private boolean isUpdated;
}
