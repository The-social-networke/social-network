package com.socialnetwork.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.socialnetwork.project.entity.enums.ChatRole;

import javax.persistence.*;

@Entity
@Table(name = "chat_users")
public class ChatUser {

    @EmbeddedId
    private ChatUserPK id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Chat.class)
    @MapsId("chatId")
    @JoinColumn(name = "chat_id", referencedColumnName = "id")
    private Chat chat;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Chat.class)
    @MapsId("userId")
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role", nullable = false)
    private ChatRole role = ChatRole.MEMBER;
}
