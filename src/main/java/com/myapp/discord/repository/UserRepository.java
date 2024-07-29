package com.myapp.discord.repository;

import com.myapp.discord.entity.DiscordUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<DiscordUser,Long> {
    Optional<DiscordUser> findByNickname(String nickname);

    Optional<DiscordUser> findFirstByEmail(String email);

}
