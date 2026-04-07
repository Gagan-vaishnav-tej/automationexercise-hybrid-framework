package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

	private WebDriver driver;
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	private By homeButton = By.cssSelector("a[href='/']");
	private By signupLoginButton = By.cssSelector("a[href='/login']");
	private By loggedInAsText = By.xpath("//div[contains(@class,'shop-menu')]//b");
	private By deleteAccountButton = By.cssSelector("a[href='/delete_account']");
	private By productsButton=By.cssSelector("a[href='/products']");
<<<<<<< HEAD
	
=======
	private By cartButton = By.xpath("//a[@href='/view_cart']");
>>>>>>> 96c8351583b95977e30499e9dff4b17c06b92e1c
	public void clickSignupLogin() {
		driver.findElement(signupLoginButton).click();
	}
	
	public boolean isLoggedInAsDisplayed()
	{
		return driver.findElement(loggedInAsText).isDisplayed();
	}
	
	public String getLoggedInAsUserText() {
		return driver.findElement(loggedInAsText).getText();
	}
	
	public void clickDeleteAccount() {
		driver.findElement(deleteAccountButton).click();
	}
	
	public boolean isHomePageVisible() {
		return driver.findElement(homeButton).isDisplayed();
	}
	
	public void clickProduct()
	{
		driver.findElement(productsButton).click();
	}
<<<<<<< HEAD
=======
	public void clickCart()
	{
		driver.findElement(cartButton).click();
	}
>>>>>>> 96c8351583b95977e30499e9dff4b17c06b92e1c
}
