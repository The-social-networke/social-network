package com.socialnetwork.project.service;

import com.socialnetwork.project.dto.UserCreateDTO;
import com.socialnetwork.project.dto.UserDTO;
import com.socialnetwork.project.dto.UserUpdateDTO;
import com.socialnetwork.project.entity.User;

public interface UserService {

    boolean create(UserCreateDTO dto);
    UserDTO getById(Long userId);
    UserDTO update(UserUpdateDTO dto);
    boolean delete(Long userId);

    User findByUsername(String username);
    User findByEmail(String email);
    User findByPhone(String phone);
}
