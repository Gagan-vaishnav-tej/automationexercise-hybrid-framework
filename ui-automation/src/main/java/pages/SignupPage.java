package pages;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import driver.DriverManager;

public class SignupPage {

	private WebDriver driver;
	public SignupPage(WebDriver driver) {
		this.driver = driver;
	}
	
	private By radioBtnTitle = By.xpath("//input[@name='title']");
	private By txtName = By.xpath("//input[@id='name']");
	private By txtPassword = By.xpath("//input[@id='password']");
	private By txtFirstname = By.cssSelector("input[data-qa='first_name']");
	private By txtLastnme = By.cssSelector("input[data-qa='last_name']");
	private By txtAddress1 = By.xpath("//input[@id='address1']");
	private By txtState = By.xpath("//input[@id='state']");
	private By txtCity = By.xpath("//input[@id='city']");
	private By txtZipcode = By.xpath("//input[@id='zipcode']");
	private By txtMobileNumber = By.xpath("//input[@id='mobile_number']");
	private By selectDay = By.xpath("//select[@id='days']");
	private By selectMonth = By.xpath("//select[@id='months']");
	private By selectYear = By.xpath("//select[@id='years']");
	private By selectCountry = By.xpath("//select[@id='country']");
	private By btnCreateAccount = By.cssSelector("button[data-qa='create-account']");
	private By accountCreatedMsg = By.xpath("//b[normalize-space()='Account Created!']");
	private By btnContinue = By.xpath("//a[normalize-space()='Continue']");
	
	public void fillAccountDetails(Map<String, String> data) {
		List<WebElement> genders = driver.findElements(radioBtnTitle);
		for(WebElement gender : genders) {
			if(gender.getAttribute("value").equalsIgnoreCase(data.get("title"))) {
				gender.click();
				break;
			}
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
		//driver.findElement(btnCreateAccount).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", driver.findElement(btnCreateAccount));
	}
	
	public String getAccountCreatedMsg() {
		return DriverManager.getWebdrDriverWait(10).until(ExpectedConditions.visibilityOfElementLocated(accountCreatedMsg)).getText();
	}
	
	public void clickContinue() {
		driver.findElement(btnContinue).click();
	}
}
