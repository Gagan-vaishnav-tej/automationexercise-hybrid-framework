package com.automationexercise.api.clients;

import com.automationexercise.api.builder.RequestBuilder;
import com.automationexercise.api.constants.ApiEndpoints;
import io.restassured.response.Response;

public class DeleteUserAccountApiClient {

    private final RequestBuilder requestBuilder = new RequestBuilder();

    public Response deleteUserAccount(String email, String password) {
        return requestBuilder.deleteAccount(ApiEndpoints.deleteUser_url, email, password);
    }
}