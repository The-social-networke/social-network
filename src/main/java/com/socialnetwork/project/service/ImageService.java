package com.socialnetwork.project.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    String saveAvatar(MultipartFile file, Long userId);
    boolean updateAvatar(String filename, MultipartFile file);
    boolean deleteAvatar(String filename);

    String saveBackground(MultipartFile file, Long userId);
    boolean updateBackground(String filename, MultipartFile file);
    boolean deleteBackground(String filename);
}
