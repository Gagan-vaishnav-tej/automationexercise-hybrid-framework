package com.automationexercise.ui.stepdefinitions;

import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import config.ConfigReader;
import com.automationexercise.ui.driver.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import logger.LogsFactory;
import com.automationexercise.ui.pages.AccountCreatedPage;
import com.automationexercise.ui.pages.AccountDeletedPage;
import com.automationexercise.ui.pages.HomePage;
import com.automationexercise.ui.pages.LoginPage;
import com.automationexercise.ui.pages.SignupPage;
import com.automationexercise.ui.utils.RandomUtil;
import com.automationexercise.ui.utils.RegisterDataUtil;

public class RegisterSteps{
	
	private WebDriver driver;
	private HomePage homePage;
	private SignupPage signupPage;
	private LoginPage loginPage;
	private AccountCreatedPage accoutCreatedPage;
	private AccountDeletedPage accountDeletedPage;
	private Logger logger = LogsFactory.getLogger(RegisterSteps.class);

	public RegisterSteps() {
		logger.info("Launching browser");
		driver = DriverFactory.getDriver();
	}

	@Given("the user is on the home page")
	public void userIsOnHomePage() {
		logger.info("the user is on the home page");
	    homePage = new HomePage(driver);
	}
	
	@When("the user clicks Signup or Login button")
	public void clicksSignupLogin() {
	    logger.info("the user clicks on Signup / Login");
	    homePage.clickSignupLogin();
	    loginPage = new LoginPage(driver);
	}

	@When("the user clicks Signup button")
	public void clicksSignup() {
	    logger.info("the user clicks on Signup");
	    loginPage.clickSignup();
	    signupPage = new SignupPage(driver);
	}

	@When("the user clicks Create Account button")
	public void clicksCreateAccount() {
	    logger.info("the user clicks on Create Account");
	    signupPage.clickCreateAccount();
	    accoutCreatedPage = new AccountCreatedPage(driver);
	}

	@When("the user clicks Continue button")
	public void clicksContinue() {
	    logger.info("the user clicks on Continue");
	    if (accoutCreatedPage.getUrl().contains("created"))
	        accoutCreatedPage.clickContinue();
	    else if (accountDeletedPage.getUrl().contains("deleted"))
	        accountDeletedPage.clickContinue();
	}

	@When("the user clicks Delete Account button")
	public void clicksDeleteAccount() {
	    logger.info("the user clicks on Delete Account");
	    homePage.clickDeleteAccount();
	    accountDeletedPage = new AccountDeletedPage(driver);
	}

	
	@Then("the user should see New User Signup! message")
	public void isNewUserSignupMessageVisible() {
	    logger.info("the user should see New User Signup! message");
	    String actualMessage = loginPage.getSignupMessage();
	    Assert.assertEquals(actualMessage, "New User Signup!");
	}

	@Then("the user should see ENTER ACCOUNT INFORMATION message")
	public void isEnterAccountInfoMessageVisible() {
	    logger.info("the user should see ENTER ACCOUNT INFORMATION message");
	    String actualMessage = signupPage.getEnterAccountInfo();
	    Assert.assertEquals(actualMessage, "ENTER ACCOUNT INFORMATION");
	}

	@Then("the user should see ACCOUNT CREATED! message")
	public void isAccountCreatedMessageVisible() {
	    logger.info("the user should see ACCOUNT CREATED! message");
	    String actualMessage = accoutCreatedPage.getAccountCreatedMsg();
	    Assert.assertEquals(actualMessage, "ACCOUNT CREATED!");
	}

	@Then("the user should see ACCOUNT DELETED! message")
	public void isAccountDeletedMessageVisible() {
	    logger.info("the user should see ACCOUNT DELETED! message");
	    String actualMessage = accountDeletedPage.getAccountDeletedMsg();
	    Assert.assertEquals(actualMessage, "ACCOUNT DELETED!");
	    accountDeletedPage.clickContinue();
	}

	
	
	@When("the user enters name and email on the sign up form")
	public void enterSignupDetails() {
		logger.info("the user enter the name and email on signup form");
		String name = ConfigReader.getProperty("register.name");
		String email = RandomUtil.generateEmail();
	    loginPage.enterSignUpDetails(name, email);
	}
	
	@When("the user enters all required account details")
	public void fillAccountDetails() {
		logger.info("the user enters all required account details");
	    Map<String, String> data = RegisterDataUtil.getRegisterData();
	    signupPage.fillAccountDetails(data);
	}
	
	@Then("the user should be logged in with their name visible")
	public void loggedInAsUser() {
		logger.info("the user should be logged in with their name visible");
	    String actual = homePage.getLoggedInAsUserText();
	    Assert.assertEquals(actual, ConfigReader.getProperty("register.name"));
	}

}
