package com.myapp.discord.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nimbusds.oauth2.sdk.id.Subject;
import jakarta.persistence.*;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Entity(name = "oauth2_user")
@Table(name = "oauth2_user")
public class OAuth2User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public OAuth2User() {
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "OAuth2User{" +
                "id=" + id +
                ", sub='" + sub + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", picURL='" + picURL + '\'' +
                ", messages=" + messages +
                ", guilds=" + guilds +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OAuth2User that = (OAuth2User) o;
        return Objects.equals(id, that.id) && Objects.equals(sub, that.sub) && Objects.equals(email, that.email) && Objects.equals(name, that.name) && Objects.equals(picURL, that.picURL) && Objects.equals(messages, that.messages) && Objects.equals(guilds, that.guilds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sub, email, name, picURL, messages, guilds);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicURL() {
        return picURL;
    }

    public void setPicURL(String picURL) {
        this.picURL = picURL;
    }

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

    public OAuth2User(Long id, List<Guild> guilds, List<Message> messages, String picURL, String name, String email, String sub) {
        this.id = id;
        this.guilds = guilds;
        this.messages = messages;
        this.picURL = picURL;
        this.name = name;
        this.email = email;
        this.sub = sub;
    }

    private String sub;
    private String email;
    private String name;
    private String picURL;

    public OAuth2User(String sub, String email, String name, String picURL) {
        this.sub = sub;
        this.email = email;
        this.name = name;
        this.picURL = picURL;
    }

    @OneToMany(mappedBy = "oauth2User", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Message> messages;

    @OneToMany(mappedBy = "oauth2User", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Guild> guilds;


}