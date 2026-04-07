package clients;

import builder.RequestBuilder;
import constants.ApiEndpoints;
import io.restassured.response.Response;
import pojo.User;

public class UserAccountApiClient {
	
private RequestBuilder requestBuilder;
	

	public UserAccountApiClient() {
		requestBuilder = new RequestBuilder();
	}


	public Response resgisterUser(User user) {
		return requestBuilder.postCreateAccount(ApiEndpoints.registerUser_url,user);
	}

}
