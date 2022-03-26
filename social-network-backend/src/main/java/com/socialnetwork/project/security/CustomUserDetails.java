package com.socialnetwork.project.security;

import com.socialnetwork.project.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {

    private Long id;
    private String email;
    private String password;
    private boolean enabled;
    private Collection<? extends GrantedAuthority> authorities;

    public static CustomUserDetails fromUserToCustomUserDetails(User user) {
        CustomUserDetails customUserDetails = new CustomUserDetails();
        customUserDetails.id = user.getId();
        customUserDetails.email = user.getEmail();
        customUserDetails.password = user.getPassword();
        customUserDetails.enabled = user.isEnabled();
        customUserDetails.authorities = user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());
        return customUserDetails;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return enabled;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
