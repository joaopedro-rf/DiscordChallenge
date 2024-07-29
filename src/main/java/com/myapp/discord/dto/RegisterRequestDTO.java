package com.myapp.discord.dto;

public record RegisterRequestDTO(
        String nickname,
        String email,
        String password
) {
}
