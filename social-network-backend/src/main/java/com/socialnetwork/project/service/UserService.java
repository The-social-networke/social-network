package com.socialnetwork.project.service;

import com.socialnetwork.project.entity.User;
import org.springframework.data.repository.query.Param;

public interface UserService extends BaseService<User, Long> {

    User findByEmail(String email);
}
