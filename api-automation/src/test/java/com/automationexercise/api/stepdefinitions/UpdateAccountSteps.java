package com.automationexercise.api.stepdefinitions;

import com.automationexercise.api.clients.UserApiClient;
import com.automationexercise.api.payloads.User;
import com.automationexercise.api.util.UserDataBuilder;
import com.automationexercise.api.util.UserFactory;
import com.automationexercise.api.validators.UserValidator;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class UpdateAccountSteps {
	private User user;
	private UserApiClient client;
	private Response response;
	private UserValidator validator;
	
	public UpdateAccountSteps()
	{
		client=new UserApiClient();
		validator=new UserValidator();
	}
	@When("user sends put request to update the account")
	public void sendsPutRequesttoUpdateUser() {
	    user = UserFactory.getUser();
	    user = UserDataBuilder.updateUser(user);
	    response=client.updateUser(user);
	}
	@Then("response code for registration should be {int}")
	public void validateStatusCodeforUpdate(Integer int1) {
	    validator.validateStatusCode(response,int1);
	}
	@Then("response should contain {string} message")
	public void validateResponseMessage(String string) {
	    validator.validateMessage(response, string);
	}
	
	@Then("response should match update user reponse JSON schema")
	public void validateUserResponseSchema() {
	    validator.validateUserResponseSchema(response);
	}

}
