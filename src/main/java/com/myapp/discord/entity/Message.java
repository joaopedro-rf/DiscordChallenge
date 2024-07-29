package com.myapp.discord.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
public class Message {
    private String content;
    private Date timestamp;
    private MessageType messageType;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    public Message(String userDisconnected, Date date, MessageType messageType, Long messageId, Channel channel, DiscordUser author) {
    }


    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public DiscordUser getDiscordUser() {
        return discordUser;
    }

    public void setDiscordUser(DiscordUser discordUser) {
        this.discordUser = discordUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @ManyToOne
    @JoinColumn(name = "channel_id", referencedColumnName = "id")
    private Channel channel;
    @ManyToOne
    @JoinColumn(name = "discord_user_id", referencedColumnName = "id")
    private DiscordUser discordUser;
    @ManyToOne
    @JoinColumn(name = "oauth2_user_id", referencedColumnName = "id")
    private OAuth2User oauth2User;

    public OAuth2User getOauth2User() {
        return oauth2User;
    }

    @Override
    public String toString() {
        return "Message{" +
                "content='" + content + '\'' +
                ", timestamp=" + timestamp +
                ", messageType=" + messageType +
                ", id=" + id +
                ", channel=" + channel +
                ", discordUser=" + discordUser +
                ", oauth2User=" + oauth2User +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(content, message.content) && Objects.equals(timestamp, message.timestamp) && messageType == message.messageType && Objects.equals(id, message.id) && Objects.equals(channel, message.channel) && Objects.equals(discordUser, message.discordUser) && Objects.equals(oauth2User, message.oauth2User);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, timestamp, messageType, id, channel, discordUser, oauth2User);
    }

    public void setOauth2User(OAuth2User oauth2User) {
        this.oauth2User = oauth2User;
    }

    public Message(String content, Date timestamp, MessageType messageType, Long id, Channel channel, DiscordUser discordUser, OAuth2User oauth2User) {
        this.content = content;
        this.timestamp = timestamp;
        this.messageType = messageType;
        this.id = id;
        this.channel = channel;
        this.discordUser = discordUser;
        this.oauth2User = oauth2User;
    }

    public Message() {
    }
}
