package com.automationexercise.api.clients;

import com.automationexercise.api.builder.RequestBuilder;
import com.automationexercise.api.constants.ApiEndpoints;
import io.restassured.response.Response;
import com.automationexercise.api.payloads.User;

public class UserApiClient {
	
private RequestBuilder requestBuilder;
	

	public UserApiClient() {
		requestBuilder = new RequestBuilder();
	}


	public Response resgisterUser(User user) {
		return requestBuilder.postCreateAccount(ApiEndpoints.registerUser_url,user);
	}
	
	public Response updateUser(User user)
	{
		return requestBuilder.putUpdateAccount(ApiEndpoints.updateUser_url,user);
	}
	
	public Response deleteUserAccount(String email, String password) {
        return requestBuilder.deleteAccount(ApiEndpoints.deleteUser_url, email, password);
    }

}
