package com.myapp.discord.service;

import com.myapp.discord.entity.Channel;
import com.myapp.discord.entity.Guild;
import com.myapp.discord.repository.ChannelRepository;
import com.myapp.discord.repository.GuildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChannelService {
    @Autowired
    private ChannelRepository channelRepository;

    @Autowired
    private GuildRepository guildRepository;

    public List<Channel> findByGuild(Guild guild) {
        return channelRepository.findByGuild(guild);
    }

    public Channel saveChannel(Channel channel, Long guildId) {
        Optional<Guild> guildOptional = guildRepository.findById(guildId);
        if (guildOptional.isEmpty()) {
            throw new RuntimeException("Guild not found");
        }
        Guild guild = guildOptional.get();
        channel.setGuild(guild);

        try {
            return channelRepository.save(channel);
        } catch (Exception e) {
            throw new RuntimeException("Error saving message", e);
        }

    }

    public Optional<Channel> findById(Long id){
        return channelRepository.findById(id);
    }


}
