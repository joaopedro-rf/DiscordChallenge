package com.myapp.discord.service;

import com.myapp.discord.entity.Channel;
import com.myapp.discord.entity.Message;
import com.myapp.discord.exception.MessageNotFoundException;
import com.myapp.discord.repository.ChannelRepository;
import com.myapp.discord.repository.GuildRepository;
import com.myapp.discord.repository.MessageRepository;
import jakarta.annotation.Nullable;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private GuildRepository guildRepository;

    @Autowired
    private ChannelRepository channelRepository;

    public List<Message> findByChannel(Channel channel) {
        return messageRepository.findByChannel(channel);
    }

    @Transactional
    public Message saveMessage(Message message, Long channelId) {

        Optional<Channel> channelOptional = channelRepository.findById(channelId);
        if (channelOptional.isEmpty()) {
            throw new RuntimeException("Channel not found");
        }
        Channel channel = channelOptional.get();
        message.setChannel(channel);

        try {
            return messageRepository.save(message);
        } catch (Exception e) {
            throw new RuntimeException("Error saving message", e);
        }
    }


    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Message getMessage(Long id) {
        return messageRepository.findById(id).orElseThrow(() -> new MessageNotFoundException(id));
    }

    public Message updateMessage(Long id, Message message) {
        Message existingMessage = getMessage(id);
        existingMessage.setContent(message.getContent());
        existingMessage.setDiscordUser(message.getDiscordUser());
        return messageRepository.save(existingMessage);
    }

    public void deleteMessage(Long id) {
        messageRepository.deleteById(id);
    }
}
