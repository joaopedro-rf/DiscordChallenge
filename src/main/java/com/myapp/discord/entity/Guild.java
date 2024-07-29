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



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    public Long getOwnerId() {
        return ownerId;
    }

    @Override
    public String toString() {
        return "Guild{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", ownerId=" + ownerId +
                ", discordUser=" + discordUser +
                ", oAuth2User=" + oauth2User +
                ", channels=" + channels +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guild guild = (Guild) o;
        return Objects.equals(id, guild.id) && Objects.equals(name, guild.name) && Objects.equals(description, guild.description) && Objects.equals(createdAt, guild.createdAt) && Objects.equals(ownerId, guild.ownerId) && Objects.equals(discordUser, guild.discordUser) && Objects.equals(oauth2User, guild.oauth2User) && Objects.equals(channels, guild.channels);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, createdAt, ownerId, discordUser, oauth2User, channels);
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public OAuth2User getoAuth2User() {
        return oauth2User;
    }

    public void setoAuth2User(OAuth2User oAuth2User) {
        this.oauth2User = oAuth2User;
    }

    public Guild(Long id, String name, String description, Date createdAt, Long ownerId, DiscordUser discordUser, OAuth2User oAuth2User, List<Channel> channels) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.ownerId = ownerId;
        this.discordUser = discordUser;
        this.oauth2User = oAuth2User;
        this.channels = channels;
    }

    private Date createdAt;
    private Long ownerId;
    @ManyToOne
    @JoinColumn(name = "discord_user_id", referencedColumnName = "id")
    private DiscordUser discordUser;
    @ManyToOne
    @JoinColumn(name = "oauth2_user_id", referencedColumnName = "id")
    private OAuth2User oauth2User;

    @OneToMany(mappedBy = "guild", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Channel> channels;

}
