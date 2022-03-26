package com.socialnetwork.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @JsonIgnore
    @NotNull
    @ManyToOne(targetEntity = Chat.class)
    @JoinColumn(name = "chat_id", nullable = false)
    private Chat chat;

    private String text;

    @CreationTimestamp
    @Column(name = "sent_at", nullable = false, updatable = false)
    private LocalDateTime sentAt;     //Spring Auditing

    @Column(name = "is_updated", nullable = false, updatable = false)
    private boolean isUpdated = false;
}
