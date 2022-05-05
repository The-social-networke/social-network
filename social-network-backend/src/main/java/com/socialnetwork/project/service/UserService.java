package com.socialnetwork.project.service;

import com.socialnetwork.project.entity.User;
import org.springframework.data.repository.query.Param;

public interface UserService {

    User createUser(User user);
    User getUserOrElseThrow(Long userId);

    User findByEmail(String email);
}
