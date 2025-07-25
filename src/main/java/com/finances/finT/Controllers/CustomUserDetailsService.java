package com.finances.finT.Controllers;

import java.util.Collections;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException; // Добавлен импорт
import org.springframework.stereotype.Service;

import com.finances.finT.models.users;
import com.finances.finT.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Searching for user: " + username);
        users user = userRepository.findByUsername(username)
            .orElseThrow(() -> {
                System.err.println("User not found: " + username);
                return new UsernameNotFoundException("User not found: " + username);
            });
        
        System.out.println("Found user: " + user.getUsername());
        
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(Collections.emptyList()) // Добавлены authorities
                .build();
    }
}