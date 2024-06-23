package com.myapp.discord.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "guild_id", referencedColumnName = "id")
    private Guild guild;

    @OneToMany(mappedBy = "channel",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Message> messages;

    public Channel() {
    }

    public List<DiscordUser> getSubscribers() {
        return subscribers;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", guild=" + guild +
                ", messages=" + messages +
                ", subscribers=" + subscribers +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Channel channel = (Channel) o;
        return Objects.equals(id, channel.id) && Objects.equals(name, channel.name) && Objects.equals(description, channel.description) && Objects.equals(createdAt, channel.createdAt) && Objects.equals(guild, channel.guild) && Objects.equals(messages, channel.messages) && Objects.equals(subscribers, channel.subscribers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, createdAt, guild, messages, subscribers);
    }

    public void setSubscribers(List<DiscordUser> subscribers) {
        this.subscribers = subscribers;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public Guild getGuild() {
        return guild;
    }

    public void setGuild(Guild guild) {
        this.guild = guild;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToMany
    @JoinTable(
            name = "channel_subscribers",
            joinColumns = @JoinColumn(name = "channel_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<DiscordUser> subscribers;

    public Channel(Long id, String name, String description, Date createdAt, Guild guild, List<Message> messages, List<DiscordUser> subscribers) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.guild = guild;
        this.messages = messages;
        this.subscribers = subscribers;
    }
}
