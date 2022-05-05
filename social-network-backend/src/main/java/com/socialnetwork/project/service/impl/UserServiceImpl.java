package com.socialnetwork.project.service.impl;

import com.socialnetwork.project.entity.User;
import com.socialnetwork.project.entity.enums.Role;
import com.socialnetwork.project.repository.UserRepository;
import com.socialnetwork.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    private void init() {
        if(findByEmail("system@gmail.com") == null) {
            User systemUser = new User().toBuilder()
                    .name("Out")
                    .surname("Work")
                    .username("OutWork")
                    .phone("380000000000")
                    .email("system@gmail.com")
                    .password(passwordEncoder.encode("admin"))
                    .roles(Set.of(Role.ROLE_USER, Role.ROLE_ADMIN))
                    .enabled(true)
                    .build();
            userRepository.save(systemUser);
        }
    }

    @Override
    public User createUser(User user) {
        user.setRoles(Set.of(Role.ROLE_USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        return userRepository.save(user);
    }

    @Override
    public User getUserOrElseThrow(Long userId) {
        return userRepository.findById(userId).orElseThrow();
    }

    /*
    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public User delete(Long id) {
        User user = userRepository.getById(id);
        userRepository.delete(user);
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAll() {
        return userRepository.findAll();
    }*/

    @Override
    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
