package com.socialnetwork.project.controller;

import com.socialnetwork.project.annotation.CurrentUser;
import com.socialnetwork.project.dto.UserDTO;
import com.socialnetwork.project.security.UserSecurity;
import com.socialnetwork.project.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserService userService;

    @GetMapping("profile")
    public UserDTO profile(
            @CurrentUser UserSecurity userSecurity
    ) {
        return userService.getById(userSecurity.getId());
    }

    @GetMapping("set-avatar")
    public String setAvatar(
            @RequestParam() MultipartFile file,
            @CurrentUser UserSecurity userDetails
    ) {
        return null;
    }
}
