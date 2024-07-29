package com.myapp.discord.repository;

import com.myapp.discord.entity.OAuth2User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OAuth2Repository extends JpaRepository<OAuth2User, Long> {

    Optional<OAuth2User> findFirstByEmail(String email);
}
