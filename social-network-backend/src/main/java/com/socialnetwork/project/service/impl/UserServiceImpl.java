package com.socialnetwork.project.service.impl;

import com.socialnetwork.project.entity.User;
import com.socialnetwork.project.repository.UserRepository;
import com.socialnetwork.project.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public User readById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public User delete(User user) {
        userRepository.delete(user);
        return user;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
