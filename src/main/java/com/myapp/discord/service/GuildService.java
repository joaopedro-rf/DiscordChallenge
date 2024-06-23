package com.myapp.discord.service;

import com.myapp.discord.entity.DiscordUser;
import com.myapp.discord.entity.Guild;

import com.myapp.discord.repository.GuildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuildService {

    @Autowired
    private GuildRepository guildRepository;

    public Guild saveGuild(Guild guild) {
        return guildRepository.save(guild);
    }
}
