package com.socialnetwork.project.mapper;

import com.socialnetwork.project.dto.UserCreateDTO;
import com.socialnetwork.project.dto.UserDTO;
import com.socialnetwork.project.dto.UserUpdateDTO;
import com.socialnetwork.project.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface UserMapper {

    User toEntity(UserCreateDTO dto);
    @Mapping(source = "userId", target = "id")
    User toEntity(UserUpdateDTO dto);

    UserDTO toUserDTO(User user);
    List<UserDTO> toUserDTO(List<User> users);
}
