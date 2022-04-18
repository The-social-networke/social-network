package com.socialnetwork.project.controller;

import com.socialnetwork.project.annotation.CurrentUser;
import com.socialnetwork.project.entity.User;
import com.socialnetwork.project.entity.enums.Role;
import com.socialnetwork.project.security.CustomUserDetails;
import com.socialnetwork.project.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserService userService;

    @GetMapping("profile")
    public ResponseEntity<User> profile(@CurrentUser CustomUserDetails userDetails) {
        User user = userService.readById(userDetails.getId());
        System.out.println("ggwp");
        return new ResponseEntity<>(
                user,
                HttpStatus.OK
        );
    }
}
