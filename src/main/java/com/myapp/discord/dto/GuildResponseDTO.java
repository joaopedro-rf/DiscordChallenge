package com.myapp.discord.dto;

public record GuildResponseDTO(
        String name,
        Long guildId,
        String inviteCode
) {
}
