package com.myapp.discord.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "guild_id", referencedColumnName = "id")
    private Guild guild;

    @OneToMany(mappedBy = "channel",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Message> messages;

    public Channel() {
    }


    public Channel(List<Message> messages, Guild guild, Date createdAt, String name) {
        this.messages = messages;
        this.guild = guild;
        this.createdAt = createdAt;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                ", guild=" + guild +
                ", messages=" + messages +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Channel channel = (Channel) o;
        return Objects.equals(id, channel.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public List<Message> getMessages() {
        if(messages == null) messages = new ArrayList<>();

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

    public Channel(Long id, Guild guild, List<Message> messages, Date createdAt, String name) {
        this.id = id;
        this.guild = guild;
        this.messages = messages;
        this.createdAt = createdAt;
        this.name = name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
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



}
