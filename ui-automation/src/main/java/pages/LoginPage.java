package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

	private WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	private By txtSignUpName = By.cssSelector("input[data-qa='signup-name']");
	private By txtSignupEmail = By.cssSelector("input[data-qa='signup-email']");
	private By btnSignup = By.cssSelector("button[data-qa='signup-button']");
	
	public void enterSignUpDetails(String name, String email) {
		driver.findElement(txtSignUpName).sendKeys(name);
		driver.findElement(txtSignupEmail).sendKeys(email);
	}
	
	public void clickSignup() {
		driver.findElement(btnSignup).click();
	}
}
