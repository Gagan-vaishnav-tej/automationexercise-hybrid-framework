package stepdefinitions;

import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import config.ConfigReader;
import driver.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import logger.LogsFactory;
import pages.HomePage;
import pages.LoginPage;
import pages.SignupPage;

public class RegisterSteps {
	
	private WebDriver driver;
	private HomePage homePage;
	private SignupPage signupPage;
	private LoginPage loginPage;
	private Logger logger = LogsFactory.getLogger(RegisterSteps.class);
	
	@Given("the user is on the home page")
	public void userOnHomePage() {
		logger.info("User is on home page");
	    driver = DriverFactory.getDriver();
	    homePage = new HomePage(driver);
	}
	
	@When("the user enters name and email on the sign up form")
	public void enterSigupDetails() {
		logger.info("Entering name and email on the signup form");
		loginPage = new LoginPage(driver);
		String email = ConfigReader.getProperty("register.email") + System.currentTimeMillis()+ "@gmail.com";
		loginPage.enterSignUpDetails(ConfigReader.getProperty("register.name"), email);
	}
	
	@When("the user submits the sign up form")
	public void submitSignup() {
		logger.info("Clicking signup button");
	    loginPage.clickSignup();
	}

	@When("the user enters all required account details")
	public void fillAccountDetails() {
		logger.info("Entering signup details");
		signupPage = new SignupPage(driver);
	    Map<String, String> data = ConfigReader.getRegisterData();
	    signupPage.fillAccountDetails(data);
	}

	@When("the user clicks {string}")
	public void clicks(String string) {
		logger.info("clicking on "+string);
		if(string.equals("Signup / Login")) {
			homePage.clickSignupLogin();
		}
	    if(string.equals("Create Account")) {
	    	signupPage.clickCreateAccount();
	    }
	    if(string.equals("Continue")) {
	    	signupPage.clickContinue();
	    }
	}
	
	@Then("the user should see {string} message")
	public void validateMsg(String expected) {
		logger.info("Validating account created message");
	    String acutal = signupPage.getAccountCreatedMsg();
	    Assert.assertEquals(acutal.toLowerCase(), expected.toLowerCase());
	}
	
	@Then("the user should be logged in with their name visible")
	public void validateName() {
		logger.info("Validating user name visibility");
	    String expected = ConfigReader.getProperty("register.name");
	    String acutal = homePage.getLoginUser();
	    Assert.assertTrue(acutal.contains(expected));
	}
}
