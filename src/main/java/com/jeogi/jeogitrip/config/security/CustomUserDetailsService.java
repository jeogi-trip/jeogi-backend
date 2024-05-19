package com.jeogi.jeogitrip.config.security;

import com.jeogi.jeogitrip.user.model.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        com.jeogi.jeogitrip.user.model.User user = userService.getUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return createUserDetails(user);


    }
    private UserDetails createUserDetails(com.jeogi.jeogitrip.user.model.User user) {
        return User.builder()
                .username(user.getEmail())
                .password(passwordEncoder.encode(user.getUserId().toString()))
                .authorities(new SimpleGrantedAuthority(user.getRole()))
                .build();
    }
}
