package com.automationexercise.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.automationexercise.ui.utils.WaitUtil;

public class AccountDeletedPage {
private WebDriver driver;
	
	public AccountDeletedPage(WebDriver driver) {
		this.driver = driver;
	}
	
	private By accountDeletedText = By.cssSelector("h2[data-qa='account-deleted']");
	private By continueButton = By.cssSelector("a[data-qa='continue-button']");
	
	public String getAccountDeletedMsg() {
		return WaitUtil.getWebDriverWait(10).until(ExpectedConditions.visibilityOfElementLocated(accountDeletedText)).getText();
	}
	
	public void clickContinue() {
		driver.findElement(continueButton).click();
	}
	
	public String getUrl() {
		return driver.getCurrentUrl();
	}
}
