package com.automationexercise.crosslayer.stepdefinitions;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.automationexercise.api.clients.UserApiClient;
import com.automationexercise.api.payloads.User;
import com.automationexercise.api.util.UserDataBuilder;
import com.automationexercise.api.validators.UserValidator;
import com.automationexercise.ui.driver.DriverFactory;
import com.automationexercise.ui.pages.AccountCreatedPage;
import com.automationexercise.ui.pages.AccountDeletedPage;
import com.automationexercise.ui.pages.HomePage;
import com.automationexercise.ui.pages.LoginPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import logger.LogsFactory;

public class HybridSteps {
	
	private WebDriver driver;
	private LoginPage loginPage;
	private HomePage homePage;
	private AccountDeletedPage accountDeletedPage;
	private AccountCreatedPage accoutCreatedPage;
	private Logger logger = LogsFactory.getLogger(HybridSteps.class);

    private User user;
	private UserApiClient client;
	private Response response;
	private UserValidator validator;
	
	public HybridSteps()
	{
		client = new UserApiClient();
		validator = new UserValidator();
		logger.info("Launching browser");
	}
	
	@When("user registers a new account via API")
	public void sendPostrequestForRegistation() {
		user = UserDataBuilder.getRegisterUser();
		response = client.resgisterUser(user);
	}

	@Then("response status code for registration via API should be {int}")
	public void validateStatusCodeForRegistration(int statusCode) {
	    validator.validateStatusCode(response, statusCode);
	}
	
	@Then("retrived response should contains {string} message")
	public void validateUserCreatedMessage(String string) {
	    validator.validateMessage(response, string);
	}
	
	@Then("retrived response should match create user reponse JSON schema")
	public void validateUserResponseSchema() {
	    validator.validateUserResponseSchema(response);
	}
	
	@Then("the email and password of the user registered via API is retrieved")
	public void the_email_and_password_of_the_user_registered_via_api_is_retrieved() {
	    
	}
	
	@Given("the registered user is on the home page")
	public void userIsOnHomePage() {
		driver = DriverFactory.getDriver();
		logger.info("the user is on the home page");
	    homePage = new HomePage(driver);
	}
	
	@When("the registered user clicks Signup or Login button")
	public void clicksSignupLogin() {
	    logger.info("the user clicks on Signup / Login");
	    homePage.clickSignupLogin();
	    loginPage = new LoginPage(driver);
	}
	
	@Then("the registered user should see Login to your account message")
	public void isLoginMessageVisible() {
		loginPage = new LoginPage(driver);
	    logger.info("the user should see Login to your account message");
	    String actualMessage = loginPage.getLoginMessage();
	    Assert.assertEquals(actualMessage, "Login to your account");
	}
	
	
	@When("the registered user enters registered email and password in login form")
	public void userEntersValidUsernameAndPassword() {
	    String email = user.getEmail();
	    String password = user.getPassword();
	    loginPage.enterLoginDetails(email, password);
	    logger.info("user enters Username in login section of the page");
	    
	}
	
	@When("the registered user clicks Login button")
	public void clicksLogin() {
	    logger.info("the user clicks on Login");
	    loginPage.clickLogin();
	}
	
	@Then("the registered user should see logged is as username")
	public void verifyLoggedInAs() {
	    logger.info("the user should see logged is as username");
	    HomePage homepage = new HomePage(driver);
	    Assert.assertTrue(homepage.isLoggedInAsDisplayed());
	}
	
	@When("the registered user clicks Delete Account button")
	public void clicksDeleteAccount() {
	    logger.info("the user clicks on Delete Account");
	    homePage.clickDeleteAccount();
	    accountDeletedPage = new AccountDeletedPage(driver);
	}
	
	@When("the registered user clicks Continue button")
	public void clicksContinue() {
	    logger.info("the user clicks on Continue");
	    accoutCreatedPage = new AccountCreatedPage(driver);
	    accountDeletedPage.clickContinue();
	}
}