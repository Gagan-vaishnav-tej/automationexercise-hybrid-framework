package clients;

import builder.RequestBuilder;
import constants.ApiEndpoints;
import io.restassured.response.Response;
import pojo.User;

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
