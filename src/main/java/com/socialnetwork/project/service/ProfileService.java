package com.socialnetwork.project.service;

import com.socialnetwork.project.dto.ProfileDTO;
import com.socialnetwork.project.dto.ProfileUpdateDTO;
import org.springframework.web.multipart.MultipartFile;

public interface ProfileService {

    ProfileDTO getById(Long userId);
    ProfileDTO update(ProfileUpdateDTO dto);

    String saveBackground(MultipartFile file, Long profileId);
    boolean updateBackground(MultipartFile file, Long profileId);
    boolean deleteBackground(Long profileId);
}
