package com.myapp.discord.controller;


import com.myapp.discord.entity.Channel;
import com.myapp.discord.entity.Guild;
import com.myapp.discord.entity.Message;
import com.myapp.discord.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.ErrorResponse;

import java.util.Objects;

@Controller
public class MessageController {
    @Autowired
    private MessageService messageService;

    @MessageMapping("/chat/{guildId}/{channelId}/sendMessage")
    @SendTo("/topic/guilds/{guildId}/channels/{channelId}")
    public ResponseEntity<?> sendMessage(@Payload Message message, @DestinationVariable Long guildId ,@DestinationVariable Long channelId){

        try {
            messageService.saveMessage(message, Long.valueOf(channelId));
            return ResponseEntity.ok(message);
        }catch ( Exception e){
            ErrorResponse errorResponse = new ErrorResponse() {
                @Override
                public HttpStatusCode getStatusCode() {
                    return null;
                }

                @Override
                public ProblemDetail getBody() {
                    return null;
                }
            };
            return ResponseEntity.status(Objects.requireNonNull(errorResponse.getStatusCode())).body(errorResponse.getBody());
        }
    }

    @MessageMapping("/chat/{guildId}/{channelId}/joinChat")
    @SendTo("/topic/guilds/{guildId}/channels/{channelId}")
    public Message userJoin(@Payload Message message, SimpMessageHeaderAccessor headerAccessor, @DestinationVariable Long guildId, @DestinationVariable Long channelId){
        Objects.requireNonNull(headerAccessor.getSessionAttributes()).put("nickname", message.getDiscordUser());
        headerAccessor.getSessionAttributes().put("guildId", guildId);
        headerAccessor.getSessionAttributes().put("channelId", channelId);
        return message;
    }


}
