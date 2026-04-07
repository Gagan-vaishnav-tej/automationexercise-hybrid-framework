package stepdefinitions;

import clients.UpdateUserAccountApiClient;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import pojo.User;
import util.UserDataBuilder;
import validators.UpdateAccountValidator;

public class updateAccountSteps {
	private User user;
	private UpdateUserAccountApiClient client;
	private Response response;
	private UpdateAccountValidator validator;
	
	public updateAccountSteps()
	{
		client=new UpdateUserAccountApiClient();
		validator=new UpdateAccountValidator();
	}
	@When("user sends put request to update the account")
	public void sendsPutRequesttoUpdateUser() {
	    user=UserDataBuilder.updateUser();
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

}
