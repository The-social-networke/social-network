package com.socialnetwork.project.mapper;

import com.socialnetwork.project.dto.AuthenticationRequestDTO;
import com.socialnetwork.project.dto.UserCreateDTO;
import com.socialnetwork.project.entity.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    User ToUser(AuthenticationRequestDTO userDTO);
    AuthenticationRequestDTO ToAuthenticationRequestDTO(User user);

    User ToUser(UserCreateDTO userDTO);
}
