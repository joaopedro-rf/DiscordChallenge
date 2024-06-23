package com.myapp.discord.repository;

import com.myapp.discord.entity.DiscordUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<DiscordUser,Long> {
    Optional<DiscordUser> findByNickname(String nickname);
}
