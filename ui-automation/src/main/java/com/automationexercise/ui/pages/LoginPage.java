package com.automationexercise.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

	private WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	private By signupText = By.xpath("//div[@class='signup-form']//h2");
	private By signupNameInput = By.xpath("//input[@placeholder='Name']");
	private By signupEmailInput = By.cssSelector("input[data-qa='signup-email']");
	private By signupButton = By.cssSelector("button[data-qa='signup-button']");
	
	private By loginText = By.xpath("//h2[normalize-space()='Login to your account']");
	private By loginEmailInput = By.xpath("//input[@data-qa='login-email']");
	private By loginPasswordInput = By.xpath("//input[@placeholder='Password']");
	private By loginButton = By.xpath("//button[normalize-space()='Login']");
	
	private By incorrectUsernameOrPasswordText = By.xpath("//p[normalize-space()='Your email or password is incorrect!']");
	
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
	
	public void enterLoginDetails(String email,String password)
	{
		driver.findElement(loginEmailInput).sendKeys(email);
		driver.findElement(loginPasswordInput).sendKeys(password);
	}
	
	public void clickLogin()
	{
		driver.findElement(loginButton).click();
	}
	
	public String getLoginMessage() {
		return driver.findElement(loginText).getText();
	}
	
	public String getInvalidUsernameOrPasswordText()
	{
		return driver.findElement(incorrectUsernameOrPasswordText).getText();
	}
}
