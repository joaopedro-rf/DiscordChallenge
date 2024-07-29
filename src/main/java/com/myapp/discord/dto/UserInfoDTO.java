package com.myapp.discord.dto;

public record UserInfoDTO(
        String sub,
        String name,
        String picture,
        String email
) { }
