package stepdefinitions;

import clients.UserAccountApiClient;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import pojo.User;
import util.UserDataBuilder;
import validators.UserAccountValidator;

public class UserAccountSteps {
	private User user;
	private UserAccountApiClient client;
	private Response response;
	private UserAccountValidator validator;
	
	public UserAccountSteps()
	{
		client = new UserAccountApiClient();
		validator = new UserAccountValidator();
	}
	
	@When("user registers a new account")
	public void sendPostrequestForRegistation() {
		user = UserDataBuilder.getRegisterUser();
		response = client.resgisterUser(user);
	}

	@Then("response status code for registration should be {int}")
	public void validateStatusCodeForRegistration(int statusCode) {
	    validator.validateStatusCode(response, statusCode);
	}
	
	@Then("reponse should contains {string} message")
	public void validateUserCreatedMessage(String string) {
	    validator.validateMessage(response, string);
	}
}
