package com.socialnetwork.project.service.impl;

import com.socialnetwork.project.dto.ProfileDTO;
import com.socialnetwork.project.dto.ProfileUpdateDTO;
import com.socialnetwork.project.entity.Profile;
import com.socialnetwork.project.mapper.ProfileMapper;
import com.socialnetwork.project.repository.ProfileRepository;
import com.socialnetwork.project.repository.UserRepository;
import com.socialnetwork.project.service.ImageService;
import com.socialnetwork.project.service.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;

    private final UserRepository userRepository;

    private final ImageService imageService;

    @Override
    public ProfileDTO getById(Long userId) {
        Optional<Profile> profile = profileRepository.getByUserId(userId);
        if (profile.isEmpty()) {
            Profile entity = new Profile().toBuilder()
                    .user(userRepository.findById(userId).orElseThrow())
                    .build();
            return profileMapper.toProfileDTO(
                    profileRepository.save(entity)
            );
        }
        return profileMapper.toProfileDTO(
                profile.get()
        );
    }

    @Override
    public ProfileDTO update(ProfileUpdateDTO dto) {
        Optional<Profile> profile = profileRepository.getByUserId(dto.getUserId());
        Profile entity = profileMapper.toEntity(dto);
        if (profile.isEmpty()) {
            return profileMapper.toProfileDTO(
                    profileRepository.save(entity)
            );
        }

        entity.setId(profile.get().getId());
        return profileMapper.toProfileDTO(
                profileRepository.save(entity)
        );
    }

    @Override
    public String saveBackground(MultipartFile file, Long profileId) {
        return null;
    }

    @Override
    public boolean updateBackground(MultipartFile file, Long profileId) {
        return false;
    }

    @Override
    public boolean deleteBackground(Long profileId) {
        return false;
    }
}
