package com.automationexercise.api.stepdefinitions;

import com.automationexercise.api.clients.UserApiClient;
import com.automationexercise.api.payloads.User;
import com.automationexercise.api.util.UserDataBuilder;
import com.automationexercise.api.validators.UserValidator;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class DeleteUserAccountSteps {

    private Response response;
    private final UserApiClient apiClient = new UserApiClient();
    private final UserValidator validator = new UserValidator();

    @When("user deletes the account")
    public void userDeletesTheAccount() {
        User user = UserDataBuilder.getUser();
        response = apiClient.deleteUserAccount(user.getEmail(), user.getPassword());
    }

    @Then("delete account response status code should be {int}")
    public void validateDeleteStatus(int statusCode) {
        validator.validateStatusCode(response, statusCode);
    }

    @Then("delete account response should contain {string} message")
    public void validateDeleteMessage(String message) {
        validator.validateMessage(response, message);
    }

    @Given("user tries to delete with invalid credentials")
    public void userTriesToDeleteWithInvalidCredentials() {
        // no setup needed
    }

    @When("user tries to delete account with invalid credentials")
    public void userTriesToDeleteAccountWithInvalidCredentials() {
        String invalidEmail = "invalid" + System.currentTimeMillis() + "@mail.com";
        String invalidPassword = "wrongpassword";

        response = apiClient.deleteUserAccount(invalidEmail, invalidPassword);
    }
    
    @Then("response should match delete user reponse SON schema")
	public void validateUserResponseSchema() {
	    validator.validateUserResponseSchema(response);
	}
}