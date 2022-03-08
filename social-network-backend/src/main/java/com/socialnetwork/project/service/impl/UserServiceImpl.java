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
    public User create(User object) {
        return userRepository.save(object);
    }

    @Transactional(readOnly = true)
    @Override
    public User readById(Integer id) {
        return userRepository.getById(id);
    }

    @Override
    public User update(User object) {
        return userRepository.save(object);
    }

    @Override
    public User delete(User object) {
        userRepository.delete(object);
        return object;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
