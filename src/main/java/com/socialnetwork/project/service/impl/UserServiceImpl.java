package com.socialnetwork.project.service.impl;

import com.socialnetwork.project.dto.ProfileDTO;
import com.socialnetwork.project.dto.UserCreateDTO;
import com.socialnetwork.project.dto.UserDTO;
import com.socialnetwork.project.dto.UserUpdateDTO;
import com.socialnetwork.project.entity.Profile;
import com.socialnetwork.project.entity.User;
import com.socialnetwork.project.entity.enums.Role;
import com.socialnetwork.project.mapper.ProfileMapper;
import com.socialnetwork.project.mapper.UserMapper;
import com.socialnetwork.project.repository.ProfileRepository;
import com.socialnetwork.project.repository.UserRepository;
import com.socialnetwork.project.security.UserSecurity;
import com.socialnetwork.project.service.ImageService;
import com.socialnetwork.project.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;

    private final ImageService imageService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean create(UserCreateDTO dto) {
        if (findByUsername(dto.getUsername()) != null) {
            throw new BadCredentialsException("Username is already exists");
        }
        if (findByEmail(dto.getEmail()) != null) {
            throw new BadCredentialsException("Email is already exists");
        }
        if (findByPhone(dto.getPhone()) != null) {
            throw new BadCredentialsException("Phone is already exists");
        }
        User entity = userMapper.toEntity(dto);
        entity.setRoles(Set.of(Role.ROLE_USER));
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        entity.setEnabled(true);
        userRepository.save(entity);
        return true;
    }

    @Override
    public UserDTO getById(Long userId) {
        return userMapper.toUserDTO(
                userRepository.findById(userId).orElseThrow()
        );
    }

    @Override
    public ProfileDTO getProfileById(Long userId) {
        return profileMapper.toProfileDTO(
            userRepository.findById(userId).orElseThrow().getProfile()
        );
    }

    @Override
    public ProfileDTO update(UserUpdateDTO dto) {
        User user = userRepository.findById(dto.getUserId()).orElseThrow();

        if (!user.getUsername().equals(dto.getUsername()) && findByUsername(dto.getUsername()) != null) {
            throw new BadCredentialsException("Username is already exists");
        }
        if (!user.getEmail().equals(dto.getEmail()) && findByEmail(dto.getEmail()) != null) {
            throw new BadCredentialsException("Email is already exists");
        }
        if (!user.getPhone().equals(dto.getPhone()) && findByPhone(dto.getPhone()) != null) {
            throw new BadCredentialsException("Phone is already exists");
        }

        user = user.toBuilder()
                .name(dto.getName() == null ? user.getName() : dto.getName())
                .surname(dto.getSurname() == null ? user.getSurname() : dto.getSurname())
                .username(dto.getUsername() == null ? user.getUsername() : dto.getUsername())
                .email(dto.getEmail() == null ? user.getEmail() : dto.getEmail())
                .phone(dto.getPhone() == null ? user.getPhone() : dto.getPhone())
                .sex(dto.getSex() == null ? user.getSex() : dto.getSex())
                .build();
        user = userRepository.save(user);

        Profile profile = user.getProfile();
        Profile entity = profileMapper.toEntity(dto);
        if (profile == null) {
            return profileMapper.toProfileDTO(
                    profileRepository.save(entity)
            );
        }

        entity.setId(profile.getId());
        return profileMapper.toProfileDTO(
                profileRepository.save(entity)
        );
    }

    @Override
    public boolean delete(Long userId) {
        User entity = userRepository.findById(userId).orElseThrow();
        entity.setEnabled(false);
        userRepository.save(entity);
        return true;
    }


    @Override
    public String saveAvatar(MultipartFile file, Long userId) {
        User entity = userRepository.findById(userId).orElseThrow();
        String avatar = imageService.saveAvatar(file, userId);
        entity.setAvatar(avatar);
        userRepository.save(entity);
        return avatar;
    }

    @Override
    public boolean updateAvatar(MultipartFile file, Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return imageService.updateAvatar(user.getAvatar(), file);
    }

    @Override
    public boolean deleteAvatar(Long userId) {
        User entity = userRepository.findById(userId).orElseThrow();
        if (imageService.deleteAvatar(entity.getAvatar())) {
            entity.setAvatar(null);
            userRepository.save(entity);
            return true;
        }
        return false;
    }

    @Override
    public String saveBackground(MultipartFile file, Long userId) {
        User entity = userRepository.findById(userId).orElseThrow();
        String background = imageService.saveBackground(file, userId);
        entity.getProfile().setBackground(background);
        userRepository.save(entity);
        return background;
    }

    @Override
    public boolean updateBackground(MultipartFile file, Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return imageService.updateBackground(user.getProfile().getBackground(), file);
    }

    @Override
    public boolean deleteBackground(Long userId) {
        User entity = userRepository.findById(userId).orElseThrow();
        if (imageService.deleteBackground(entity.getProfile().getBackground())) {
            entity.getProfile().setBackground(null);
            userRepository.save(entity);
            return true;
        }
        return false;
    }

    @Override
    public List<UserDTO> searchChats(String search, UserSecurity userSecurity) {
        List<User> users = userRepository.searchUsers(search.toLowerCase());
        if (userSecurity != null) {
            users = users.stream()
                    .filter(user -> user.getId() != userSecurity.getId())
                    .toList();
        }
        return userMapper.toUserDTO(
                users
        );
    }


    @Override
    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
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
