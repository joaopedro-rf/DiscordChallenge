package com.myapp.discord.security;

import com.myapp.discord.dto.UserInfoDTO;
import com.myapp.discord.service.OAuth2Service;
import com.nimbusds.openid.connect.sdk.claims.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.introspection.OAuth2IntrospectionAuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

public class GoogleOpaqueTokenInstrospector implements OpaqueTokenIntrospector {

    private static final Logger log = LoggerFactory.getLogger(GoogleOpaqueTokenInstrospector.class);
    @Autowired
    private WebClient userInfoClient;
    @Autowired
    private OAuth2Service oAuth2Service;

    public GoogleOpaqueTokenInstrospector(WebClient userInfoClient) {
    }

    @Override
    public OAuth2AuthenticatedPrincipal introspect(String token) {
        UserInfoDTO userInfo = userInfoClient.get()
                .uri( uriBuilder -> uriBuilder
                        .path("/oauth2/v3/userinfo")
                        .queryParam("access_token", token)
                        .build())
                .retrieve()
                .bodyToMono(UserInfoDTO.class)
                .block();
        Map<String, Object> attributes = new HashMap<>();
        if(userInfo == null){
            throw new RuntimeException("User was not found");
        }
        attributes.put("sub", userInfo.sub());
        attributes.put("name", userInfo.name());
        saveUserToDatabase(new UserInfoDTO(userInfo.sub(), userInfo.name(), userInfo.picture(),userInfo.email() ));
        return new OAuth2IntrospectionAuthenticatedPrincipal(userInfo.name(), attributes, null );
    }

    private void saveUserToDatabase(UserInfoDTO userInfo) {
        oAuth2Service.saveUser(userInfo);
    }

}


