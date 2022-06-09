package com.socialnetwork.project.service;

import com.socialnetwork.project.dto.ProfileDTO;
import com.socialnetwork.project.dto.ProfileUpdateDTO;

public interface ProfileService {

    ProfileDTO getById(Long userId);
    ProfileDTO update(ProfileUpdateDTO dto);
}
