package com.automationexercise.ui.pages;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.automationexercise.ui.utils.WaitUtil;

public class SignupPage {

	private WebDriver driver;
	public SignupPage(WebDriver driver) {
		this.driver = driver;
	}
	
	private By enterAccountInfoText = By.xpath("//b[contains(.,'Account Information')]");
	private By titleMrRadiobtn = By.cssSelector("input[id='id_gender1']");
	private By titleMrsRadiobtn = By.cssSelector("input[id='id_gender2']");
	private By passwordInput = By.cssSelector("input[data-qa='password']");
	private By newsLetterCheckbox = By.cssSelector("input[id='newsletter']");
	private By specialOffersCheckbox = By.cssSelector("input[id='optin']");
	private By firstNameInput = By.cssSelector("input[data-qa='first_name']");
	private By lastNameInput = By.cssSelector("input[data-qa='last_name']");
	private By companyInput = By.cssSelector("input[data-qa='company']");
	private By addressInput = By.cssSelector("input[data-qa='address']");
	private By address2Input = By.cssSelector("input[data-qa='address2']");
	private By stateInput = By.cssSelector("input[data-qa='state']");
	private By cityInput = By.cssSelector("input[data-qa='city']");
	private By zipcodeInput = By.cssSelector("input[data-qa='zipcode']");;
	private By mobileNumberInput = By.cssSelector("input[data-qa='mobile_number']");
	private By daySelect = By.cssSelector("select[id='days']");
	private By monthSelect = By.cssSelector("select[id='months']");
	private By yearSelect = By.cssSelector("select[id='years']");
	private By countrySelect = By.cssSelector("select[id='country']");
	private By createAccountButton = By.cssSelector("button[data-qa='create-account']");
	
	public void fillAccountDetails(Map<String, String> data) {
		if(data.get("title").equalsIgnoreCase("mr")) {
			driver.findElement(titleMrRadiobtn).click();;
		}
		else {
			driver.findElement(titleMrsRadiobtn).click();
		}
		driver.findElement(passwordInput).sendKeys(data.get("password"));
		new Select(driver.findElement(daySelect)).selectByVisibleText(data.get("dob.day"));
		new Select(driver.findElement(monthSelect)).selectByVisibleText(data.get("dob.month"));
		new Select(driver.findElement(yearSelect)).selectByVisibleText(data.get("dob.year"));
		driver.findElement(newsLetterCheckbox).click();
		driver.findElement(specialOffersCheckbox).click();
		driver.findElement(firstNameInput).sendKeys(data.get("firstname")); 
		driver.findElement(lastNameInput).sendKeys(data.get("lastname"));
		driver.findElement(companyInput).sendKeys(data.get("company"));
		driver.findElement(addressInput).sendKeys(data.get("address"));
		driver.findElement(address2Input).sendKeys(data.get("address"));
		new Select(driver.findElement(countrySelect)).selectByVisibleText(data.get("country"));
		driver.findElement(stateInput).sendKeys(data.get("state"));
		driver.findElement(cityInput).sendKeys(data.get("city"));
		driver.findElement(zipcodeInput).sendKeys(data.get("zipcode"));
		driver.findElement(mobileNumberInput).sendKeys(data.get("mobile"));
	}
	
	public void clickCreateAccount() {
		WebElement createBtn = WaitUtil.getWebDriverWait(10).until(ExpectedConditions.
				elementToBeClickable(createAccountButton));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView({block : 'center'});", createBtn);
		try {
			Thread.sleep(1000);
			createBtn.click();
		}
		catch(InterruptedException | ElementClickInterceptedException e) {
			js.executeScript("arguments[0].click();", createBtn);
		}
	}
	
	public String getEnterAccountInfo() {
		return driver.findElement(enterAccountInfoText).getText();
	}
	
}
