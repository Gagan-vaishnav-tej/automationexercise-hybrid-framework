package com.automationexercise.api.stepdefinitions;

import com.automationexercise.api.clients.UserAccountApiClient;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import com.automationexercise.api.payloads.User;
import com.automationexercise.api.util.UserDataBuilder;
import com.automationexercise.api.validators.UserValidator;

public class UserAccountSteps {
	private User user;
	private UserAccountApiClient client;
	private Response response;
	private UserValidator validator;
	
	public UserAccountSteps()
	{
		client = new UserAccountApiClient();
		validator = new UserValidator();
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
	
	@Then("response should contains {string} message")
	public void validateUserCreatedMessage(String string) {
	    validator.validateMessage(response, string);
	}
	
	@Then("response should match create user reponse JSON schema")
	public void validateUserResponseSchema() {
	    validator.validateUserResponseSchema(response);
	}
}
