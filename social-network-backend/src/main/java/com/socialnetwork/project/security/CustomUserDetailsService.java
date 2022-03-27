package com.socialnetwork.project.security;

import com.socialnetwork.project.entity.User;
import com.socialnetwork.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor//(onConstructor = @__(@Autowired))
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public CustomUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findByEmail(email);

        if(user == null) {
            throw new UsernameNotFoundException("User with email: " + email + " is not found!");
        }

        return CustomUserDetails.fromUserToCustomUserDetails(user);
    }
}