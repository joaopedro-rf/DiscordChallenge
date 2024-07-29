package com.myapp.discord.service;

import com.myapp.discord.dto.UserInfoDTO;
import com.myapp.discord.entity.OAuth2User;
import com.myapp.discord.repository.OAuth2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OAuth2Service {

    @Autowired
    private final OAuth2Repository oAuth2Repository;

    public OAuth2Service(OAuth2Repository oAuth2Repository) {
        this.oAuth2Repository = oAuth2Repository;
    }

    public void saveUser(UserInfoDTO userInfo){
        if(oAuth2Repository.findFirstByEmail(userInfo.email()).isPresent()){
            return;
        }
        OAuth2User user = new OAuth2User();
        user.setSub(userInfo.sub());
        user.setEmail(userInfo.email());
        user.setName(userInfo.name());
        user.setPicURL(userInfo.picture());

        oAuth2Repository.save(user);
    }
}
