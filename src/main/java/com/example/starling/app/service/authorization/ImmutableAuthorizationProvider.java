package com.example.starling.app.service.authorization;


public class ImmutableAuthorizationProvider implements AuthorizationProvider {

    private final String authorization;

    public ImmutableAuthorizationProvider(String authorization) {
        this.authorization = authorization;
    }

    public static AuthorizationProvider fromOauthToken(String jwtToken) {
        return new ImmutableAuthorizationProvider(String.format("Bearer %s", jwtToken));
    }

    @Override
    public String getEncodedAuthorization() {
        return this.authorization;
    }
}

