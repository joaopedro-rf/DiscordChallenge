package com.myapp.discord.security;

import com.myapp.discord.dto.UserInfoDTO;
import com.myapp.discord.service.OAuth2Service;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication;
import org.springframework.security.oauth2.server.resource.introspection.OAuth2IntrospectionAuthenticatedPrincipal;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;

@Component
public class OAuth2UserInfoFilter extends OncePerRequestFilter {

    @Autowired
    private OAuth2Service oAuth2Service;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if (request.getUserPrincipal() instanceof BearerTokenAuthentication authentication) {
            OAuth2IntrospectionAuthenticatedPrincipal principal = (OAuth2IntrospectionAuthenticatedPrincipal) authentication.getPrincipal();

            Map<String, Object> attributes = principal.getAttributes();
            String sub = (String) attributes.get("sub");
            String name = (String) attributes.get("name");
            String email = (String) attributes.get("email");
            String picture = (String) attributes.get("picture");

            UserInfoDTO userInfo = new UserInfoDTO(sub, name, email, picture);
            saveUserToDatabase(userInfo);
        }

        filterChain.doFilter(request, response);
    }

    private void saveUserToDatabase(UserInfoDTO userInfo) {
        oAuth2Service.saveUser(userInfo);
    }
}