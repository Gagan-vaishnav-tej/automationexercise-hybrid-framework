package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

	private WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	private By signupText = By.xpath("//div[@class='signup-form']//h2");
	private By signupNameInput = By.cssSelector("input[data-qa='signup-name']");
	private By signupEmailInput = By.cssSelector("input[data-qa='signup-email']");
	private By signupButton = By.cssSelector("button[data-qa='signup-button']");
	
	public void enterSignUpDetails(String name, String email) {
		driver.findElement(signupNameInput).sendKeys(name);
		driver.findElement(signupEmailInput).sendKeys(email);
	}
	
	public void clickSignup() {
		driver.findElement(signupButton).click();
	}
	
	public String getSignupMessage() {
		return driver.findElement(signupText).getText();
	}
}
