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
import pages.AccountCreatedPage;
import pages.AccountDeletedPage;
import pages.HomePage;
import pages.LoginPage;
import pages.SignupPage;
import utils.RandomUtil;
import utils.RegisterDataUtil;

public class RegisterSteps {
	
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
	
	@Then("the home page should be visible successfully")
	public void isHomePageVisible() {
		logger.info("home page should be visible");
	   Assert.assertTrue(homePage.isHomePageVisible());
	}
	
	@When("the user clicks {string}")
	public void clicks(String button) {
		logger.info("the user clicks on "+button);
		switch(button) {
			case "Signup / Login":
				homePage.clickSignupLogin();
	    		loginPage = new LoginPage(driver);
	    		break;
			case "Signup":
				loginPage.clickSignup();
	    		signupPage = new SignupPage(driver);
	    		break;
			case "Create Account":
				signupPage.clickCreateAccount();
	    		accoutCreatedPage = new AccountCreatedPage(driver);
	    		break;
			case "Continue":
				if(accoutCreatedPage.getUrl().contains("created"))
					accoutCreatedPage.clickContinue();
				else if(accountDeletedPage.getUrl().contains("deleted"))
					accountDeletedPage.clickContinue();
				break;
			case "Delete Account":
				homePage.clickDeleteAccount();
		    	accountDeletedPage = new AccountDeletedPage(driver);
		    	break;
		    default:
		    	throw new IllegalArgumentException("Invalid Button");
		}
	}
	
	@Then("the user should see {string} message")
	public void isMessageVisible(String message) {
		logger.info("the user should see "+message+" message");
		String actualMessage = "";
		switch(message) {
			case "New User Signup!":
				actualMessage = loginPage.getSignupMessage();
		    	Assert.assertEquals(actualMessage, message);
		    	break;
			case "ENTER ACCOUNT INFORMATION":
				actualMessage = signupPage.getEnterAccountInfo();
		    	Assert.assertEquals(actualMessage, message);
		    	break;
			case "ACCOUNT CREATED!":
				actualMessage = accoutCreatedPage.getAccountCreatedMsg();
		    	Assert.assertEquals(actualMessage, message);
		    	break;
			case "ACCOUNT DELETED!":
				actualMessage = accountDeletedPage.getAccountDeletedMsg();
		    	Assert.assertEquals(actualMessage, message);
		    	accountDeletedPage.clickContinue();
		    	break;
		    default:
		    	throw new IllegalArgumentException("Invalid Messsae");
				
		}
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
	    String actual = homePage.getLoggedInAsUser();
	    Assert.assertEquals(actual, ConfigReader.getProperty("register.name"));
	}

}
