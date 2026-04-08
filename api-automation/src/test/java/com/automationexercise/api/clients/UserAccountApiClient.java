package com.automationexercise.api.clients;

import com.automationexercise.api.builder.RequestBuilder;
import com.automationexercise.api.constants.ApiEndpoints;
import io.restassured.response.Response;
import com.automationexercise.api.payloads.User;

public class UserAccountApiClient {
	
private RequestBuilder requestBuilder;
	

	public UserAccountApiClient() {
		requestBuilder = new RequestBuilder();
	}


	public Response resgisterUser(User user) {
		return requestBuilder.postCreateAccount(ApiEndpoints.registerUser_url,user);
	}

}
