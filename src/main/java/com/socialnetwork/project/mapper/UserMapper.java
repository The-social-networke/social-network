package com.socialnetwork.project.mapper;

import com.socialnetwork.project.dto.AuthenticationDTO;
import com.socialnetwork.project.dto.UserCreateDTO;
import com.socialnetwork.project.dto.UserDTO;
import com.socialnetwork.project.entity.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    User ToEntity(AuthenticationDTO userDTO);
    User ToEntity(UserCreateDTO userDTO);

    UserDTO toUserDTO(User user);
}
