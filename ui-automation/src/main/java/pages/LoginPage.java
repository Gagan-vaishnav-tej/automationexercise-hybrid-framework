package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import config.ConfigReader;

public class LoginPage {

	private WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	private By txtSignUpName = By.xpath("//input[@placeholder='Name']");
	private By txtSignupEmail = By.xpath("//input[@data-qa='signup-email']");
	private By btnSignup = By.xpath("//button[normalize-space()='Signup']");
	
	public void enterSignUpDetails(String name, String email) {
		driver.findElement(txtSignUpName).sendKeys(name);
		if(!email.equalsIgnoreCase(ConfigReader.getProperty("register.email"))){
			driver.findElement(txtSignupEmail).sendKeys(email);
		}
		else {
			String genEmail = "test" + System.currentTimeMillis() + "@gmail.com";
			driver.findElement(txtSignupEmail).sendKeys(genEmail);
		}
	}
	
	public void clickSignup() {
		driver.findElement(btnSignup).click();
	}
}
