package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage {
	private WebDriver driver;
	public ProductPage(WebDriver driver)
	{
		this.driver=driver;
	}
	private By addToCartButton=By.cssSelector("a[data-product-id='1']");
	private By continueShoppingButton=By.xpath("//button[normalize-space()='Continue Shopping']");
	private By add2ndProduct=By.cssSelector("a[data-product-id='2']");
	private By viewCartButton=By.xpath("//u[normalize-space()='View Cart']");
	private By product1InCartTxt = By.xpath("//tr[@id='product-1']//td[@class='cart_description']//h4/a");
	private By product2InCartTxt = By.xpath("//tr[@id='product-2']//td[@class='cart_description']//h4/a");
	private By product1NameTxt = By.xpath("(//div[@class='productinfo text-center']//p)[1]");
	private By product2NameTxt = By.xpath("(//div[@class='productinfo text-center']//p)[2]");
	private String storedProduct1;
	private String storedProduct2;
	private By product1PriceTxt=By.cssSelector("tr[id='product-1'] td[class='cart_price'] p");
	private By product2PriceTxt=By.cssSelector("tr[id='product-2'] td[class='cart_price'] p");
	private By product1QuanityTxt=By.cssSelector("tr[id='product-1'] button[class='disabled']");
	private By product2QuanityTxt=By.cssSelector("tr[id='product-2'] button[class='disabled']");
	private By product1TotalPriceTxt=By.cssSelector("tr[id='product-1'] p[class='cart_total_price']");
	private By product2TotalPriceTxt=By.cssSelector("tr[id='product-2'] p[class='cart_total_price']");
	
	public void addFirstProductToCart()
	{
		storedProduct1=driver.findElement(product1NameTxt).getText();
		driver.findElement(addToCartButton).click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void clickOnContinueButton()
	{
		driver.findElement(continueShoppingButton).click();
	}
	public void addSecondProductToCart()
	{
		storedProduct2=driver.findElement(product2NameTxt).getText();
		driver.findElement(add2ndProduct).click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void clickViewCart()
	{
		driver.findElement(viewCartButton).click();
	}
	public String[] getStoredProducts()
	{
		return new String[] {storedProduct1,storedProduct2};
	}
	public String[] getProductsInCart()
	{
		String product1=driver.findElement(product1InCartTxt).getText();
		String product2=driver.findElement(product2InCartTxt).getText();
		return new String[] {product1,product2};
	}
	public int[]getTotalPrice()
	{
		int product1Price=Integer.parseInt(driver.findElement(product1PriceTxt).getText().replace("Rs. ",""));
		int product2Price=Integer.parseInt(driver.findElement(product2PriceTxt).getText().replace("Rs. ", ""));
		int product1Quantity=Integer.parseInt(driver.findElement(product1QuanityTxt).getText());
		int product2Quantity=Integer.parseInt(driver.findElement(product2QuanityTxt).getText());
		int product1AcutalTotalPrice=Integer.parseInt(driver.findElement(product1TotalPriceTxt).getText().replace("Rs. ",""));
		int product2AcutalTotalPrice=Integer.parseInt(driver.findElement(product2TotalPriceTxt).getText().replace("Rs. ",""));
		return new int[] {product1Price*product1Quantity,product2Price*product2Quantity,product1AcutalTotalPrice,product2AcutalTotalPrice};
		
	}
}
