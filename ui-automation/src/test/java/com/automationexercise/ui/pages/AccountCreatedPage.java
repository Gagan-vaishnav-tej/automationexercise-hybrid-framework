package com.automationexercise.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.automationexercise.ui.utils.WaitUtil;

public class AccountCreatedPage {
	private WebDriver driver;
	
	public AccountCreatedPage(WebDriver driver) {
		this.driver = driver;
	}
	
	private By accountCreatedText = By.cssSelector("h2[data-qa='account-created']");
	private By continueButton = By.cssSelector("a[data-qa='continue-button']");
	
	public String getAccountCreatedMsg() {
		return WaitUtil.getWebDriverWait(10).until(ExpectedConditions.visibilityOfElementLocated(accountCreatedText)).getText();
	}
	
	public void clickContinue() {
		driver.findElement(continueButton).click();
	}
	
	public String getUrl() {
		return driver.getCurrentUrl();
	}
}
