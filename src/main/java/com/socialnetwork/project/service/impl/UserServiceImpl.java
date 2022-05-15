package com.socialnetwork.project.service.impl;

import com.socialnetwork.project.dto.UserCreateDTO;
import com.socialnetwork.project.dto.UserDTO;
import com.socialnetwork.project.dto.UserUpdateDTO;
import com.socialnetwork.project.entity.User;
import com.socialnetwork.project.entity.enums.Role;
import com.socialnetwork.project.mapper.UserMapper;
import com.socialnetwork.project.repository.UserRepository;
import com.socialnetwork.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    public User create(UserCreateDTO dto) {
        User user = userMapper.ToEntity(dto);
        user.setRoles(Set.of(Role.ROLE_USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        return userRepository.save(user);
    }

    @Override
    public UserDTO getById(Long userId) {
        return userMapper.toUserDTO(
                userRepository.findById(userId).orElseThrow()
        );
    }

    @Override
    public User update(UserUpdateDTO dto) {
        return null;
    }

    @Override
    public boolean delete(Long userId) {
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public User findByPhone(String phone) {
        return userRepository.findByPhone(phone).orElse(null);
    }
}
