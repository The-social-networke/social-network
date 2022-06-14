package com.socialnetwork.project.mapper;

import com.socialnetwork.project.dto.*;
import com.socialnetwork.project.entity.Post;
import com.socialnetwork.project.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(uses = {UserMapper.class})
public interface PostMapper {

    @Mapping(source = "userId", target = "user.id")
    Post toEntity(PostCreateDTO dto);
    @Mapping(source = "userId", target = "user.id")
    Post toEntity(PostUpdateDTO dto);

    PostDTO toPostDTO(Post post);
    List<PostDTO> toPostDTO(List<Post> posts);
}
