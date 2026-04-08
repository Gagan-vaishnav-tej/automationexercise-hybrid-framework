package stepdefinitions;

import clients.DeleteUserAccountApiClient;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;
import io.restassured.response.Response;
import pojo.User;
import util.UserDataBuilder;
import validators.DeleteUserAccountValidator;

public class DeleteUserAccountSteps {

    private Response response;
    private final DeleteUserAccountApiClient apiClient = new DeleteUserAccountApiClient();
    private final DeleteUserAccountValidator validator = new DeleteUserAccountValidator();

    @When("user deletes the account")
    public void userDeletesTheAccount() {
        User user = UserDataBuilder.getUser();
        response = apiClient.deleteUserAccount(user.getEmail(), user.getPassword());
    }

    @Then("delete account response status code should be {int}")
    public void validateDeleteStatus(int statusCode) {
        validator.validateDeleteStatusCode(response, statusCode);
    }

    @Then("delete account response should contain {string} message")
    public void validateDeleteMessage(String message) {
        validator.validateDeleteResponseMessage(response, message);
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
}