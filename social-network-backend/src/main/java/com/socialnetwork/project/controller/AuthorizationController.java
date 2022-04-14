package com.socialnetwork.project.controller;

import com.socialnetwork.project.dto.AuthenticationRequestDTO;
import com.socialnetwork.project.dto.UserCreateDTO;
import com.socialnetwork.project.entity.User;
import com.socialnetwork.project.mapper.UserMapper;
import com.socialnetwork.project.security.CustomUserDetails;
import com.socialnetwork.project.security.jwt.JwtProvider;
import com.socialnetwork.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthorizationController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/registration")
    public ResponseEntity<String> registration(@RequestBody UserCreateDTO userDTO) {
        User user = userMapper.ToUser(userDTO);
        userService.create(user);
        return ResponseEntity.ok("Registration is successful");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthenticationRequestDTO authentication) {
        CustomUserDetails user = (CustomUserDetails) authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authentication.getEmail(),
                        authentication.getPassword()))
                .getPrincipal();

        String token = jwtProvider.generateToken(user);
        return ResponseEntity.ok(token);
    }
}
