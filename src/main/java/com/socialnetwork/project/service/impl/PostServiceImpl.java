package com.socialnetwork.project.service.impl;

import com.socialnetwork.project.dto.PostCreateDTO;
import com.socialnetwork.project.dto.PostDTO;
import com.socialnetwork.project.dto.PostUpdateDTO;
import com.socialnetwork.project.entity.Post;
import com.socialnetwork.project.mapper.PostMapper;
import com.socialnetwork.project.repository.PostRepository;
import com.socialnetwork.project.repository.UserRepository;
import com.socialnetwork.project.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Override
    public PostDTO create(PostCreateDTO dto) {
        Post entity = postMapper.toEntity(dto);
        return postMapper.toPostDTO(
                postRepository.save(entity)
        );
    }

    @Override
    @Transactional(readOnly = true)
    public PostDTO getById(Long postId) {
        return postMapper.toPostDTO(
                postRepository.findById(postId).orElseThrow()
        );
    }

    @Override
    public PostDTO update(PostUpdateDTO dto) {
        Post entity = postRepository.findById(dto.getId()).orElseThrow();
        entity.setText(dto.getText());
        return postMapper.toPostDTO(
                postRepository.save(entity)
        );
    }

    @Override
    public boolean delete(Long postId) {
        postRepository.deleteById(postId);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostDTO> getUserPosts(Long userId) {
        return postMapper.toPostDTO(
                postRepository.getUserPosts(userId)
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostDTO> getSubscriptionPosts(Long userId) {
        return null;
    }
}
