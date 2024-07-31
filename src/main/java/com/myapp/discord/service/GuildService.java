package com.myapp.discord.service;

import com.myapp.discord.dto.GuildJWTDTO;
import com.myapp.discord.dto.GuildOauth2DTO;
import com.myapp.discord.dto.GuildResponseDTO;
import com.myapp.discord.entity.Channel;
import com.myapp.discord.entity.DiscordUser;
import com.myapp.discord.entity.Guild;
import com.myapp.discord.entity.OAuth2User;
import com.myapp.discord.repository.GuildRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GuildService {

    @Autowired
    private GuildRepository guildRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private OAuth2Service oAuth2Service;

    public GuildResponseDTO saveGuildWithOauth(GuildOauth2DTO guildOauth2DTO) {
        OAuth2User oAuth2User = new OAuth2User();
        oAuth2User = oAuth2Service.findByIdOrThrowException(guildOauth2DTO.oauthUserId());

        Guild guild = new Guild();
        UUID uuid = UUID.randomUUID();

        Channel generalChannel = new Channel(new ArrayList<>(), guild, new Date(), "general");
        guild.getChannels().add(generalChannel);
        guild.addOauth2User(oAuth2User);
        guild.setInviteCode(uuid.toString());
        guild.getOauth2User().add(oAuth2User);
        guild.setCreatedAt(new Date());
        guild.setName(guildOauth2DTO.name());
        guildRepository.save(guild);
        return new GuildResponseDTO(guild.getName(), guild.getId(), guild.getInviteCode());
    }
    @Transactional
    public GuildResponseDTO saveGuildWithJWT(GuildJWTDTO guildJWTDTO) {
        DiscordUser discordUser = userService.findByIdOrThrowException(guildJWTDTO.discordUserId());

        Guild guild = new Guild();
        UUID uuid = UUID.randomUUID();

        Channel generalChannel = new Channel(new ArrayList<>(), guild, new Date(), "general");
        guild.getChannels().add(generalChannel);

        guild.addDiscordUser(discordUser);
        guild.getDiscordUser().add(discordUser);
        guild.setInviteCode(uuid.toString());
        guild.setCreatedAt(new Date());
        guild.setName(guildJWTDTO.name());
        guildRepository.save(guild);
        return new GuildResponseDTO(guild.getName(), guild.getId(), guild.getInviteCode());
    }
}
