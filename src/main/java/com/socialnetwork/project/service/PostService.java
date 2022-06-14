package com.socialnetwork.project.service;

import com.socialnetwork.project.dto.*;
import com.socialnetwork.project.entity.User;

import java.util.List;

public interface PostService {

    PostDTO create(PostCreateDTO dto);
    PostDTO getById(Long postId);
    PostDTO update(PostUpdateDTO dto);
    boolean delete(Long postId);
    List<PostDTO> getUserPosts(Long userId);
    List<PostDTO> getSubscriptionPosts(Long userId);
}
