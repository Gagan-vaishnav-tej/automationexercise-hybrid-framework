package com.automationexercise.api.stepdefinitions;

import org.apache.logging.log4j.Logger;

import com.automationexercise.api.clients.UserApiClient;
import com.automationexercise.api.payloads.User;
import com.automationexercise.api.util.UserDataBuilder;
import com.automationexercise.api.util.UserFactory;
import com.automationexercise.api.validators.UserValidator;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import logger.LogsFactory;

public class UpdateUserSteps {
	private User user;
	private UserApiClient client;
	private Response response;
	private UserValidator validator;
	private Logger logger = LogsFactory.getLogger(UpdateUserSteps.class);
	
	public UpdateUserSteps()
	{
		client=new UserApiClient();
		validator=new UserValidator();
	}
	@When("user sends put request to update the account")
	public void sendsPutRequesttoUpdateUser() {
		logger.info("user sends put request to update the account");
	    user = UserFactory.getUser();
	    user = UserDataBuilder.updateUser(user);
	    response=client.updateUser(user);
	}
	@Then("response code for registration should be {int}")
	public void validateStatusCodeforUpdate(Integer code) {
		logger.info("response code for registration should be "+ code);
	    validator.validateStatusCode(response, code);
	}
	@Then("response should contain {string} message")
	public void validateResponseMessage(String string) {
		logger.info("response should contain {string} message");
	    validator.validateMessage(response, string);
	}
	
	@Then("response should match update user response JSON schema")
	public void validateUserResponseSchema() {
		logger.info("response should match update user reponse JSON schema");
	    validator.validateUserResponseSchema(response);
	}

}
