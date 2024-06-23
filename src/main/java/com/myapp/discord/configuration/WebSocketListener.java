package com.myapp.discord.configuration;

import com.myapp.discord.entity.*;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.security.SecureRandom;
import java.util.Date;

@Component
public class WebSocketListener {

    private final SimpMessageSendingOperations messageTemplate;


    public WebSocketListener(SimpMessageSendingOperations messageTemplate) {
        this.messageTemplate = messageTemplate;
    }

    @EventListener
    public void DisconnectListener(SessionDisconnectEvent event){
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        DiscordUser author = (DiscordUser) headerAccessor.getSessionAttributes().get("author");
        Guild guild = (Guild) headerAccessor.getSessionAttributes().get("guild");
        Channel channel = (Channel) headerAccessor.getSessionAttributes().get("channel");

        SecureRandom random = new SecureRandom();
        Long messageId = random.nextLong();
        if(author!= null && guild!= null && channel!= null){
            var chatMessage = new Message
                    (
                            "User Disconnected",
                            new Date(),
                            MessageType.LEAVE,
                            messageId,
                            channel,
                            author
                            );
            messageTemplate.convertAndSend("/topic/guilds/" + guild.getId() + "/channels/" + channel.getId(), chatMessage);
        }
    }
}
