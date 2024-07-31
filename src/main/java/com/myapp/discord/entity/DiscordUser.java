package com.myapp.discord.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

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


    public List<Message> getMessages() {
        if(messages == null) messages = new ArrayList<>();
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
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
    public String toString() {
        return "DiscordUser{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", messages=" + messages +
                ", guilds=" + guilds +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiscordUser that = (DiscordUser) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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

    @OneToMany(mappedBy = "discordUser", cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Message> messages;

    public Set<Guild> getGuilds() {
        if(guilds == null) guilds = new HashSet<>();
        return guilds;
    }

    public void setGuilds(Set<Guild> guilds) {
        this.guilds = guilds;
    }


    public DiscordUser(Long id, String nickname, Date createdAt, Date updatedAt, String email, String password, List<Message> messages, Set<Guild> guilds) {
        this.id = id;
        this.nickname = nickname;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.email = email;
        this.password = password;
        this.messages = messages;
        this.guilds = guilds;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "discord_user_server",
            joinColumns = @JoinColumn(name = "discord_user_id"),
            inverseJoinColumns = @JoinColumn(name = "guild_id")

    )
    @JsonIgnore
    private Set<Guild> guilds = new HashSet<>();

    public void addGuild(Guild guild) {
        this.guilds.add(guild);
        guild.getDiscordUser().add(this);
    }

}
