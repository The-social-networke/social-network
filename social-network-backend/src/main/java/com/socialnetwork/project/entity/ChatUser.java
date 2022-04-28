package com.socialnetwork.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.socialnetwork.project.entity.enums.ChatRole;
import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = "chat_users")
public class ChatUser {

    @EmbeddedId
    private ChatUserPK id = new ChatUserPK();

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Chat.class)
    @MapsId("chatId")
    @JoinColumn(name = "chat_id", referencedColumnName = "id")
    private Chat chat;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    @MapsId("userId")
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role", nullable = false)
    private ChatRole role = ChatRole.MEMBER;
}
