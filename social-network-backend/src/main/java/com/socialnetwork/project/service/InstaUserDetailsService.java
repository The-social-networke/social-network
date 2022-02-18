package com.socialnetwork.project.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class InstaUserDetailsService implements UserDetailsService {

    //todo change it
    //@Autowired
    //private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //new UsernameNotFoundException("Username not found");
        return null;
//
//        return userService
//                .findByUsername(username)
//                .map(InstaUserDetails::new)
//                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }
}
