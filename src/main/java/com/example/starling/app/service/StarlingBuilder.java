package com.example.starling.app.service;

import com.example.starling.app.service.authorization.AuthorizationProvider;
import com.example.starling.app.service.authorization.ImmutableAuthorizationProvider;

public class StarlingBuilder {

    private AuthorizationProvider authorizationProvider;

    /**
     * Instantiates a new Starling builder.
     */
    public StarlingBuilder() {
    }

    private StarlingBuilder withAuthorizationProvider(final AuthorizationProvider authorizationProvider) {
        this.authorizationProvider = authorizationProvider;
        return this;
    }

    public StarlingBuilder withAccessToken(String accessToken) {
        return withAuthorizationProvider(ImmutableAuthorizationProvider.fromOauthToken(accessToken));
    }


    public Starling build() {
        return new Starling(authorizationProvider);
    }

}

