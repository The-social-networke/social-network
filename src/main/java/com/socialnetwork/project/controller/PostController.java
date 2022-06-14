package com.socialnetwork.project.controller;

import com.socialnetwork.project.annotation.CurrentUser;
import com.socialnetwork.project.dto.PostCreateDTO;
import com.socialnetwork.project.dto.PostDTO;
import com.socialnetwork.project.dto.PostUpdateDTO;
import com.socialnetwork.project.security.UserSecurity;
import com.socialnetwork.project.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("posts")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PostController {

    private final PostService postService;

    @GetMapping
    public List<PostDTO> getUserPosts(
            @CurrentUser UserSecurity userSecurity
    ) {
        return postService.getUserPosts(userSecurity.getId());
    }

    @GetMapping("{id}")
    public PostDTO details(
            @PathVariable("id") Long postId
    ) {
        return postService.getById(postId);
    }

    @PostMapping
    public PostDTO create(
            @RequestBody PostCreateDTO dto,
            @CurrentUser UserSecurity userSecurity
    ) {
        dto.setUserId(userSecurity.getId());
        PostDTO postDTO = postService.create(dto);
        return postService.getById(postDTO.getId());
    }

    @PutMapping
    public PostDTO update(
            @RequestBody PostUpdateDTO dto,
            @CurrentUser UserSecurity userSecurity
    ) {
        dto.setUserId(userSecurity.getId());
        return postService.update(dto);
    }

    @DeleteMapping("{id}")
    public boolean delete(
            @PathVariable("id") Long postId
    ) {
        return postService.delete(postId);
    }
}
