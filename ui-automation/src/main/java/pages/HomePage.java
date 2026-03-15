package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

	private WebDriver driver;
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	private By btnSignupLogin = By.cssSelector("a[href='/login']");
	private By txtLoginUser = By.xpath("//div[contains(@class, \"shop-menu\")]//li[10]");
	
	public void clickSignupLogin() {
		driver.findElement(btnSignupLogin).click();
	}
	
	public String getLoginUser() {
		return driver.findElement(txtLoginUser).getText();
	}
}
