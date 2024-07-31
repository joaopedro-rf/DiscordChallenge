package com.myapp.discord.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.*;

@Entity(
        name = "guild"
)
@Table(
        name = "guild"
)
public class Guild {

    public Guild() {
    }

    public Set<DiscordUser> getDiscordUser() {
        if (discordUser == null) discordUser = new HashSet<>();

        return discordUser;
    }

    public void setDiscordUser(Set<DiscordUser> discordUser) {
        this.discordUser = discordUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guild guild = (Guild) o;
        return Objects.equals(id, guild.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Guild{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", inviteCode='" + inviteCode + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

    public Guild(String name, String inviteCode, Date createdAt, Set<DiscordUser> discordUser, Set<OAuth2User> oauth2User, List<Channel> channels) {
        this.name = name;
        this.inviteCode = inviteCode;
        this.createdAt = createdAt;
        this.discordUser = discordUser;
        this.oauth2User = oauth2User;
        this.channels = channels;
    }

    public Set<OAuth2User> getOauth2User() {
        if (oauth2User == null) oauth2User = new HashSet<>();

        return oauth2User;
    }

    public void setOauth2User(Set<OAuth2User> oauth2User) {

        this.oauth2User = oauth2User;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<Channel> getChannels() {
        if (channels == null) channels = new ArrayList<>();

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


    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    private String inviteCode;

    public Guild(Long id, List<Channel> channels, Set<OAuth2User> oauth2User, Date createdAt, String inviteCode, String name) {
        this.id = id;
        this.channels = channels;
        this.oauth2User = oauth2User;
        this.createdAt = createdAt;
        this.inviteCode = inviteCode;
        this.name = name;
    }

    public Guild(Set<DiscordUser> discordUser,Date createdAt, String inviteCode, String name, Long id, List<Channel> channels) {
        this.discordUser = discordUser;
        this.createdAt = createdAt;
        this.inviteCode = inviteCode;
        this.name = name;
        this.id = id;
        this.channels = channels;
    }

    private Date createdAt;

    @ManyToMany(mappedBy = "guilds")
    private Set<DiscordUser> discordUser = new HashSet<>();

    @ManyToMany(mappedBy = "guilds")
    private Set<OAuth2User> oauth2User = new HashSet<>();

    @OneToMany(mappedBy = "guild", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Channel> channels;

    public void addDiscordUser(DiscordUser user) {
        this.discordUser.add(user);
        user.getGuilds().add(this);
    }
    public void addOauth2User(OAuth2User user) {
        this.oauth2User.add(user);
        user.getGuilds().add(this);
    }
}
