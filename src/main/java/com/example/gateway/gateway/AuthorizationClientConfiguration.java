package com.example.gateway.gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@Configuration
@EnableOAuth2Client
public class AuthorizationClientConfiguration {

    @Bean
    protected OAuth2ProtectedResourceDetails resourceDetails() {
        String uri = "http://localhost:9999/uaa/oauth/token";
        ResourceOwnerPasswordResourceDetails resourceOwnerPasswordResourceDetails = new ResourceOwnerPasswordResourceDetails();
        resourceOwnerPasswordResourceDetails.setAccessTokenUri(uri);
        resourceOwnerPasswordResourceDetails.setClientId("my-trusted-client");
        resourceOwnerPasswordResourceDetails.setClientSecret("secret");
        resourceOwnerPasswordResourceDetails.setUsername("newUser");
        resourceOwnerPasswordResourceDetails.setPassword("newUser");
        resourceOwnerPasswordResourceDetails.setGrantType("password");
        resourceOwnerPasswordResourceDetails.setTokenName("movieServiceToken");
        return resourceOwnerPasswordResourceDetails;
    }

    @Bean
    protected OAuth2ProtectedResourceDetails clientResourceDetails() {
        String uri = "http://localhost:9999/uaa/oauth/token";
        ClientCredentialsResourceDetails clientCredentialsResourceDetails = new ClientCredentialsResourceDetails();
        clientCredentialsResourceDetails.setAccessTokenUri(uri);
        clientCredentialsResourceDetails.setClientId("my-trusted-client");
        clientCredentialsResourceDetails.setClientSecret("secret");
        clientCredentialsResourceDetails.setGrantType("client_credentials");
        clientCredentialsResourceDetails.setTokenName("movieServiceToken");
        return clientCredentialsResourceDetails;
    }

    @Bean
    public OAuth2RestOperations restOperations() {
        AccessTokenRequest accessTokenRequest = new DefaultAccessTokenRequest();
        return new OAuth2RestTemplate(clientResourceDetails(), new DefaultOAuth2ClientContext(accessTokenRequest));
    }


}
