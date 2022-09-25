package com.example.starling.app.service;

import com.example.starling.app.service.authorization.AuthorizationProvider;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

public class StarlingClient {

    private static final String STARLING_API = "https://api.starlingbank.com/api/v2";
    private static final String AUTHORIZATION_HEADER_NAME = "Authorization";

    private final AuthorizationProvider authorizationProvider;

    public StarlingClient(AuthorizationProvider authorizationProvider) {
        this.authorizationProvider = authorizationProvider;
    }

    public String get(String path) {

        if (!path.startsWith("/")) {
            path = "/" + path;
        }

        HttpResponse<JsonNode> response = Unirest.get(STARLING_API + path)
                .header(AUTHORIZATION_HEADER_NAME, authorizationProvider.getEncodedAuthorization())
                .asJson();

        return response.getBody().toString();
    }
}
