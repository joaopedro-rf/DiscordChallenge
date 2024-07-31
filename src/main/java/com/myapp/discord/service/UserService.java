package com.myapp.discord.service;

import com.myapp.discord.entity.DiscordUser;
import com.myapp.discord.entity.OAuth2User;
import com.myapp.discord.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Optional<DiscordUser> findByUsername(String nickname) {
        return userRepository.findByNickname(nickname);
    }

    public DiscordUser findByIdOrThrowException(Long id){
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public DiscordUser getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        String username = authentication.getName();
        return userRepository.findByNickname(username).orElseThrow();
    }

}
