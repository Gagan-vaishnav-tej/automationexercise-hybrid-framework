package setpdefinitions;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import config.ConfigReader;
import driver.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.LoginPage;
import pages.SignupPage;

public class RegisterSteps {
	
	private WebDriver driver;
	private HomePage homePage;
	private SignupPage signupPage;
	private LoginPage loginPage;
	
	@Given("the user is on the home page")
	public void userOnHomePage() {
	    driver = DriverManager.getDriver();
	    homePage = new HomePage(driver);
	}
	
	@When("the user enters name and email on the sign up form")
	public void enterSigupDetails() {
		loginPage = new LoginPage(driver);
		loginPage.enterSignUpDetails(ConfigReader.getProperty("register.name"), ConfigReader.getProperty("register.email"));
	}
	
	@When("the user submits the sign up form")
	public void submitSignup() {
	    loginPage.clickSignup();
	}

	@When("the user enters all required account details")
	public void fillAccountDetails() {
		signupPage = new SignupPage(driver);
	    Map<String, String> data = ConfigReader.getRegisterData();
	    signupPage.fillAccountDetails(data);
	}

	@When("the user clicks {string}")
	public void the_user_clicks(String string) {
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
	    String acutal = signupPage.getAccountCreatedMsg();
	    Assert.assertEquals(acutal.toLowerCase(), expected.toLowerCase());
	}
	
	@Then("the user should be logged in with their name visible")
	public void validateName() {
	    String expected = ConfigReader.getProperty("register.name");
	    String acutal = homePage.getLoginUser();
	    Assert.assertTrue(acutal.contains(expected));
	}
}
