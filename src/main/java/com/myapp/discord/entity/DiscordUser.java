package com.myapp.discord.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity(
        name = "discord_user"
)
@Table(
        name = "discord_user"
)
public class DiscordUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public DiscordUser() {
    }

    private String nickname;
    private Date createdAt;
    private Date updatedAt;
    private String email;
    private String password;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiscordUser that = (DiscordUser) o;
        return Objects.equals(id, that.id) && Objects.equals(nickname, that.nickname) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt) && Objects.equals(email, that.email) && Objects.equals(password, that.password) && Objects.equals(subscribedChannels, that.subscribedChannels) && Objects.equals(messages, that.messages) && Objects.equals(guilds, that.guilds);
    }

    @Override
    public String toString() {
        return "DiscordUser{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", subscribedChannels=" + subscribedChannels +
                ", messages=" + messages +
                ", guilds=" + guilds +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nickname, createdAt, updatedAt, email, password, subscribedChannels, messages, guilds);
    }

    @ManyToMany
    @JoinTable(
            name = "channel_subscribers",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "channel_id")
    )
    private List<Channel> subscribedChannels;

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<Guild> getGuilds() {
        return guilds;
    }

    public void setGuilds(List<Guild> guilds) {
        this.guilds = guilds;
    }

    public List<Channel> getSubscribedChannels() {
        return subscribedChannels;
    }

    public void setSubscribedChannels(List<Channel> subscribedChannels) {
        this.subscribedChannels = subscribedChannels;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("USER_ROLE"));
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToMany(mappedBy = "discordUser", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Message> messages;

    @OneToMany(mappedBy = "discordUser", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Guild> guilds;

    public DiscordUser(Long id, String nickname, Date createdAt, Date updatedAt, String email, String password, List<Channel> subscribedChannels, List<Message> messages, List<Guild> guilds) {
        this.id = id;
        this.nickname = nickname;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.email = email;
        this.password = password;
        this.subscribedChannels = subscribedChannels;
        this.messages = messages;
        this.guilds = guilds;
    }
}
