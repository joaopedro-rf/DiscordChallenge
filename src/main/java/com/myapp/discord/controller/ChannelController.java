package com.myapp.discord.controller;

import com.myapp.discord.entity.Channel;
import com.myapp.discord.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/guilds/{guildId}/channels")
public class ChannelController {
    @Autowired
    private ChannelService channelService;


//    @GetMapping
//    public ResponseEntity<List<Channel>> getChannels(@PathVariable Long guildId) {
//        return new ResponseEntity<>()
//    }

    @GetMapping
    public ResponseEntity<Optional<Channel>> getChannel(@PathVariable Long channelId){
        return new ResponseEntity<>(channelService.findById(channelId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Channel> createChannel(@PathVariable Long guildId, @RequestBody Channel channel) {
        channelService.saveChannel(channel, guildId);
        return new ResponseEntity<>(channel, HttpStatus.CREATED);
    }
}
