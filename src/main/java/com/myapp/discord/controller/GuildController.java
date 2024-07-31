package com.myapp.discord.controller;

import com.myapp.discord.dto.GuildJWTDTO;
import com.myapp.discord.dto.GuildOauth2DTO;
import com.myapp.discord.dto.GuildResponseDTO;
import com.myapp.discord.entity.Guild;
import com.myapp.discord.repository.GuildRepository;
import com.myapp.discord.service.GuildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/guilds")
public class GuildController {

    @Autowired
    private GuildService guildService;
    @Autowired
    private GuildRepository guildRepository;


//    @GetMapping
//    public ResponseEntity<List<Guild>> getGuilds() {
//
//    }

    @PostMapping("/jwt")
    public ResponseEntity<GuildResponseDTO> createGuildWithJWT(@RequestBody GuildJWTDTO guild) {
        GuildResponseDTO guildResponseDTO = guildService.saveGuildWithJWT(guild);

        return new ResponseEntity<>(guildResponseDTO, HttpStatus.CREATED);
    }

    @PostMapping("/oauth2")
    public ResponseEntity<GuildResponseDTO> createGuildWithOauth2(@RequestBody GuildOauth2DTO guild) {
        GuildResponseDTO guildResponseDTO = guildService.saveGuildWithOauth(guild);


        return new ResponseEntity<>(guildResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Optional<Guild> returnGuilds(@PathVariable Long id){
        return guildRepository.findById(id);
    }

//    @DeleteMapping("/{guildId}")
//    public ResponseEntity<Void> deleteGuild(@PathVariable Long guildId) {
//
//    }

}
