package com.myapp.discord.repository;

import com.myapp.discord.entity.Channel;
import com.myapp.discord.entity.Guild;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChannelRepository extends JpaRepository<Channel,Long> {
    List<Channel> findByGuild(Guild guild);
}
