package com.myapp.discord.controller;

import com.myapp.discord.entity.Guild;
import com.myapp.discord.service.GuildService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guilds")
public class GuildController {

    @Autowired
    private GuildService guildService;


//    @GetMapping
//    public ResponseEntity<List<Guild>> getGuilds() {
//
//    }

    @PostMapping
    public ResponseEntity<Guild> createGuild(@RequestBody Guild guild) {
        return new ResponseEntity<>(guild, HttpStatus.CREATED);
    }

//    @DeleteMapping("/{guildId}")
//    public ResponseEntity<Void> deleteGuild(@PathVariable Long guildId) {
//
//    }

}
