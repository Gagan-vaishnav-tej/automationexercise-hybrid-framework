package com.automationexercise.api.stepdefinitions;

import org.apache.logging.log4j.Logger;

import com.automationexercise.api.clients.UserApiClient;
import com.automationexercise.api.payloads.User;
import com.automationexercise.api.util.UserFactory;
import com.automationexercise.api.validators.UserValidator;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import logger.LogsFactory;

public class DeleteUserSteps {

	private User user;
    private Response response;
    private final UserApiClient apiClient = new UserApiClient();
    private final UserValidator validator = new UserValidator();
    private Logger logger = LogsFactory.getLogger(DeleteUserSteps.class);

    @When("user deletes the account")
    public void userDeletesTheAccount() {
    	logger.info("user deletes the account");
        user = UserFactory.getUser();
        response = apiClient.deleteUserAccount(user.getEmail(), user.getPassword());
    }

    @Then("delete account response status code should be {int}")
    public void validateDeleteStatus(int statusCode) {
    	logger.info("delete account response status code should be "+ statusCode);
        validator.validateStatusCode(response, statusCode);
    }

    @Then("delete account response should contain {string} message")
    public void validateDeleteMessage(String message) {
    	logger.info("delete account response should contain {string} message");
        validator.validateMessage(response, message);
    }

    @Given("user tries to delete with invalid credentials")
    public void userTriesToDeleteWithInvalidCredentials() {
        // no setup needed
    	logger.info("user tries to delete with invalid credentials");
    }

    @When("user tries to delete account with invalid credentials")
    public void userTriesToDeleteAccountWithInvalidCredentials() {
    	logger.info("user tries to delete account with invalid credentials");
        String invalidEmail = "invalid" + System.currentTimeMillis() + "@mail.com";
        String invalidPassword = "wrongpassword";

        response = apiClient.deleteUserAccount(invalidEmail, invalidPassword);
    }
    
    @Then("response should match delete user response JSON schema")
	public void validateUserResponseSchema() {
    	logger.info("response should match delete user response JSON schema");
	    validator.validateUserResponseSchema(response);
	}
}