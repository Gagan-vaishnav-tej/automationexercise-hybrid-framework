package com.automationexercise.api.stepdefinitions;

import org.apache.logging.log4j.Logger;

import com.automationexercise.api.clients.UserApiClient;
import com.automationexercise.api.payloads.User;
import com.automationexercise.api.util.UserDataBuilder;
import com.automationexercise.api.validators.UserValidator;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import logger.LogsFactory;

public class CreateUserSteps {
	private User user;
	private UserApiClient client;
	private Response response;
	private UserValidator validator;
	private Logger logger = LogsFactory.getLogger(CreateUserSteps.class);
	
	public CreateUserSteps()
	{
		client = new UserApiClient();
		validator = new UserValidator();
	}
	
	@When("user registers a new account")
	public void sendPostrequestForRegistation() {
		logger.info("user registers a new account");
		user = UserDataBuilder.getcreateUserData();
		response = client.resgisterUser(user);
	}

	@Then("response status code for registration should be {int}")
	public void validateStatusCodeForRegistration(int statusCode) {
		logger.info("response status code for registration should be "+ statusCode);
	    validator.validateStatusCode(response, statusCode);
	}
	
	@Then("response should contains {string} message")
	public void validateUserCreatedMessage(String string) {
		logger.info("response should contains "+ string +" message");
	    validator.validateMessage(response, string);
	}
	
	@Then("response should match create user response JSON schema")
	public void validateUserResponseSchema() {
	    validator.validateUserResponseSchema(response);
	    // Deleting user so that is does not reside in website
	    client.deleteUserAccount(user.getEmail(), user.getPassword());
	    logger.info("response should match create user reponse JSON schema");
	}
}
