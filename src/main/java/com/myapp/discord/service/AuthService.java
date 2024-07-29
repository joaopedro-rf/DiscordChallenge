package com.myapp.discord.service;

import com.myapp.discord.dto.*;
import com.myapp.discord.entity.DiscordUser;
import com.myapp.discord.repository.UserRepository;
import com.myapp.discord.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;


@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public ResponseEntity<LoginResponseDTO> login(LoginRequestDTO loginRequestDTO) {
        DiscordUser discordUser = userRepository.findFirstByEmail(loginRequestDTO.email())
                .orElseThrow(()-> new RuntimeException("User not found"));

        if (discordUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (checkPassword(loginRequestDTO.password(), discordUser.getPassword())) {
                String token = tokenService.generateToken(discordUser);
                return ResponseEntity.ok(new LoginResponseDTO(token));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

    }

    private boolean checkPassword(String rawPassword, String encodedPassword) {
        return new BCryptPasswordEncoder().matches(rawPassword, encodedPassword);
    }


    public ResponseEntity<ResponseDTO> register(RegisterRequestDTO registerRequestDTO){
        Optional<DiscordUser> user = userRepository.findFirstByEmail(registerRequestDTO.email());

        if(user.isEmpty()){
            DiscordUser newUser = new DiscordUser();
            newUser.setPassword(passwordEncoder.encode(registerRequestDTO.password()));
            newUser.setEmail(registerRequestDTO.email());
            newUser.setNickname(registerRequestDTO.nickname());
            newUser.setCreatedAt(new Date());
            userRepository.save(newUser);


            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return ResponseEntity.badRequest().build();
    }


}
