package com.socialnetwork.project.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.socialnetwork.project.entity.Comment;
import com.socialnetwork.project.entity.User;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class PostDTO {

    private Long id;

    private UserDTO user;

    private String text;

    private String photo;

    private LocalDateTime createdAt;

    private Set<User> likedPosts;

    private Set<Comment> comments;
}
