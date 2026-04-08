package com.automationexercise.api.clients;

import com.automationexercise.api.builder.RequestBuilder;
import com.automationexercise.api.constants.ApiEndpoints;
import io.restassured.response.Response;
import com.automationexercise.api.payloads.User;

public class UpdateUserAccountApiClient {
	private RequestBuilder requestBuilder;
	
	public UpdateUserAccountApiClient()
	{
		requestBuilder=new RequestBuilder();
	}
	public Response updateUser(User user)
	{
		return requestBuilder.putUpdateAccount(ApiEndpoints.updateUser_url,user);
	}
	
}
