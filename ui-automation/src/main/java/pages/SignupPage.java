package pages;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import driver.DriverFactory;

public class SignupPage {

	private WebDriver driver;
	public SignupPage(WebDriver driver) {
		this.driver = driver;
	}
	
	private By btnMr = By.cssSelector("input[id='id_gender1']");
	private By btnMrs = By.cssSelector("input[id='id_gender2']");
	private By txtName = By.cssSelector("input[id='name']");
	private By txtPassword = By.cssSelector("input[data-qa='password']");
	private By txtFirstname = By.cssSelector("input[data-qa='first_name']");
	private By txtLastnme = By.cssSelector("input[data-qa='last_name']");
	private By txtAddress1 = By.cssSelector("input[data-qa='address']");
	private By txtState = By.cssSelector("input[data-qa='state']");
	private By txtCity = By.cssSelector("input[data-qa='city']");
	private By txtZipcode = By.cssSelector("input[data-qa='zipcode']");;
	private By txtMobileNumber = By.cssSelector("input[data-qa='mobile_number']");
	private By selectDay = By.cssSelector("select[id='days']");
	private By selectMonth = By.cssSelector("select[id='months']");
	private By selectYear = By.cssSelector("select[id='years']");
	private By selectCountry = By.cssSelector("select[id='country']");
	private By btnCreateAccount = By.cssSelector("button[data-qa='create-account']");
	private By txtAccountCreatedMsg = By.cssSelector("h2[data-qa='account-created']");
	private By btnContinue = By.cssSelector("a[data-qa='continue-button']");
	
	public void fillAccountDetails(Map<String, String> data) {
		if(data.get("title") == "Mr") {
			driver.findElement(btnMr).click();;
		}
		else {
			driver.findElement(btnMrs).click();
		}
		driver.findElement(txtName).sendKeys(data.get("name"));
		driver.findElement(txtPassword).sendKeys(data.get("password"));
		new Select(driver.findElement(selectDay)).selectByVisibleText(data.get("dob.day"));
		new Select(driver.findElement(selectMonth)).selectByVisibleText(data.get("dob.month"));
		new Select(driver.findElement(selectYear)).selectByVisibleText(data.get("dob.year"));
		driver.findElement(txtFirstname).sendKeys(data.get("firstname")); 
		driver.findElement(txtLastnme).sendKeys(data.get("lastname"));
		driver.findElement(txtAddress1).sendKeys(data.get("address1"));
		new Select(driver.findElement(selectCountry)).selectByVisibleText(data.get("country"));
		driver.findElement(txtState).sendKeys(data.get("state"));
		driver.findElement(txtCity).sendKeys(data.get("city"));
		driver.findElement(txtZipcode).sendKeys(data.get("zipcode"));
		driver.findElement(txtMobileNumber).sendKeys(data.get("mobile"));
	}
	
	public void clickCreateAccount() {
		driver.findElement(btnCreateAccount).click();
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].click();", driver.findElement(btnCreateAccount));
	}
	
	public String getAccountCreatedMsg() {
		return DriverFactory.getWebdrDriverWait(10).until(ExpectedConditions.visibilityOfElementLocated(txtAccountCreatedMsg)).getText();
	}
	
	public void clickContinue() {
		driver.findElement(btnContinue).click();
	}
}
