package com.automationexercise.ui.stepdefinitions;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import config.ConfigReader;
import com.automationexercise.ui.driver.DriverFactory;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import logger.LogsFactory;
import com.automationexercise.ui.pages.HomePage;
import com.automationexercise.ui.pages.LoginPage;

public class LoginSteps {
	
	private WebDriver driver;
	private LoginPage loginPage;
	private Logger logger = LogsFactory.getLogger(LoginSteps.class);
	
	
	public LoginSteps() {
		logger.info("Launching browser");
		driver = DriverFactory.getDriver();
	}
	

	@When("the user enters valid email and valid password in login form")
	public void userEntersValidUsernameAndPassword() {
	    String email = ConfigReader.getProperty("login.email");
	    String password = ConfigReader.getProperty("login.password");
	    loginPage.enterLoginDetails(email, password);
	    logger.info("user enters Username in login section of the page");
	    
	}
	
	@When("the user enters invalid email and password in login form")
	public void userEntersInvalidUsernameAndPassword() {
	    String email = ConfigReader.getProperty("login.invalidemail");
	    String password = ConfigReader.getProperty("login.password");
	    loginPage.enterLoginDetails(email, password);
	    logger.info("user enters Username in login section of the page");
	    
	}
	
	@When("the user clicks Login button")
	public void clicksLogin() {
	    logger.info("the user clicks on Login");
	    loginPage.clickLogin();
	}

	@Then("the user should see Login to your account message")
	public void isLoginMessageVisible() {
		loginPage = new LoginPage(driver);
	    logger.info("the user should see Login to your account message");
	    String actualMessage = loginPage.getLoginMessage();
	    Assert.assertEquals(actualMessage, "Login to your account");
	}
	
	
	@Then("the user should see logged is as username")
	public void verifyLoggedInAs() {
	    logger.info("the user should see logged is as username");
	    HomePage homepage = new HomePage(driver);
	    Assert.assertTrue(homepage.isLoggedInAsDisplayed());
	}
	
	@Then("the user should see {string}")
	public void verifyIncorrectUsernamePassword(String errorMessage)
	{
		logger.info("the user should see incorrect username or password as error message");
		String actualMessage = loginPage.getInvalidUsernameOrPasswordText();
		Assert.assertEquals(actualMessage, errorMessage);
	}

}
