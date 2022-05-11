package com.socialnetwork.project.controller;

import com.socialnetwork.project.annotation.CurrentUser;
import com.socialnetwork.project.entity.User;
import com.socialnetwork.project.security.UserSecurity;
import com.socialnetwork.project.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserService userService;

    @GetMapping("profile")
    public ResponseEntity<User> profile(
            @CurrentUser UserSecurity userSecurity
    ) {
        return ResponseEntity.ok(userService.getById(userSecurity.getId()));
    }

    @GetMapping("set-avatar")
    public String setAvatar(
            @RequestParam() MultipartFile file,
            @CurrentUser UserSecurity userDetails
    ) {
        return null;
    }
}
