package com.example.gateway.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    @Autowired
    private OAuth2RestOperations oAuth2RestTemplate;
    

    public String getAuthorizationToken() {
        OAuth2AccessToken accessToken = oAuth2RestTemplate.getAccessToken();
        return "Bearer " + accessToken.getValue();
    }

}
