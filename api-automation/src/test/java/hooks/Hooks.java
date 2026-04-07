package hooks;

import io.cucumber.java.Before;
import builder.RequestBuilder;
import constants.ApiEndpoints;
import pojo.User;
import util.UserDataBuilder;

public class Hooks {

	@Before("@update")
	public void createUserBeforeUpdate() {
	    System.out.println("Creating user before update scenario...");

	    User createdUser = UserDataBuilder.getRegisterUser();

	    RequestBuilder requestBuilder = new RequestBuilder();
	    requestBuilder.postCreateAccount(ApiEndpoints.registerUser_url, createdUser);

	    UserDataBuilder.setUser(createdUser);
	}
}