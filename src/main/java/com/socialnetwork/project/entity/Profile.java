package com.socialnetwork.project.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "profiles")
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "sequence-generator")
    @SequenceGenerator(name = "sequence-generator", sequenceName = "id_seq",
            initialValue = 20,
            allocationSize = 1)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER, targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "instagram")
    private String instagram;

    @Column(name = "telegram")
    private String telegram;

    @Column(name = "facebook")
    private String facebook;

    @Column(name = "discord")
    private String discord;

    @Column(name = "twitter")
    private String twitter;

    @Column(name = "linked_in")
    private String linkedIn;

    @Column(name = "skype")
    private String skype;

    @Column(name = "birthday")
    private LocalDate birthday;
}
