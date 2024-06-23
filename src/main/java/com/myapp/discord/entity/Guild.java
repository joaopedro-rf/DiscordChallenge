package com.myapp.discord.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity(
        name = "guild"
)
@Table(
        name = "guild"
)
public class Guild {

    public Guild() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Guild{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", discordUser=" + discordUser +
                ", channels=" + channels +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guild guild = (Guild) o;
        return Objects.equals(id, guild.id) && Objects.equals(name, guild.name) && Objects.equals(description, guild.description) && Objects.equals(createdAt, guild.createdAt) && Objects.equals(discordUser, guild.discordUser) && Objects.equals(channels, guild.channels);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, createdAt, discordUser, channels);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public DiscordUser getDiscordUser() {
        return discordUser;
    }

    public void setDiscordUser(DiscordUser discordUser) {
        this.discordUser = discordUser;
    }

    public List<Channel> getChannels() {
        return channels;
    }

    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }

    public Long getId() {
        return id;
    }

    public Guild(Long id, String name, String description, Date createdAt, DiscordUser discordUser, List<Channel> channels) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.discordUser = discordUser;
        this.channels = channels;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Date createdAt;
    private Long ownerId;
    @ManyToOne
    @JoinColumn(name = "discord_user_id", referencedColumnName = "id")
    private DiscordUser discordUser;

    @OneToMany(mappedBy = "guild", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Channel> channels;

}
