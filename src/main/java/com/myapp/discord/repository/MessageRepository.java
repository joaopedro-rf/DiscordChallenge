package com.myapp.discord.repository;

import com.myapp.discord.entity.Channel;
import com.myapp.discord.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByChannel(Channel channel);
}
