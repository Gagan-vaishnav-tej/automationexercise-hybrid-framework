package stepdefinitions;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import driver.DriverFactory;
<<<<<<< HEAD
import io.cucumber.java.en.Given;
=======
>>>>>>> 96c8351583b95977e30499e9dff4b17c06b92e1c
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import logger.LogsFactory;
import pages.HomePage;
import pages.ProductPage;

public class AddProductSteps {
	private WebDriver driver;
	private HomePage homepage;
	private ProductPage productpage;
	private Logger logger=LogsFactory.getLogger(AddProductSteps.class);
	public AddProductSteps()
	{
		logger.info("Launching Browser");
		driver=DriverFactory.getDriver();
	}
	
	@When("the user Click Products button")
	public void clickProduct() {
		homepage=new HomePage(driver);
		homepage.clickProduct();
	    productpage=new ProductPage(driver);
	    logger.info("user Click Products button");
	}

	@When("Hover over first product and click Add to cart")
	public void hoveroverFirstProductAndClickAddToCart() {
	    productpage.addFirstProductToCart();
	    logger.info("Hover over first product and click Add to cart");
	}

	@When("Click Continue Shopping button")
	public void clickContinueShopping() {
	    productpage.clickOnContinueButton();
	    logger.info("Click Continue Shopping button");
	}

	@When("user Hover over second product and click Add to cart")
	public void hoveroverSecondtProductAndClickAddToCart() {
		productpage.addSecondProductToCart();
		logger.info("user Hover over second product and click Add to cart");
	}

	@When("Click view Cart button")
	public void clickViewCart() {
		logger.info("Click view Cart button");
	    productpage.clickViewCart();
	    
	}

	@Then("user verify both products are added to Cart")
	public void verifyBothProductsAreInCart() {
		logger.info("user verify both products are added to Cart");
	    Assert.assertEquals(productpage.getStoredProducts()[0], productpage.getProductsInCart()[0]);
	    Assert.assertEquals(productpage.getStoredProducts()[1], productpage.getProductsInCart()[1]);
	}

	@Then("user should verify their prices, quantity and total price")
	public void verifyPricesQuantityTotal_price() {
	    logger.info("user should verify their prices, quantity and total price");
	    Assert.assertEquals(productpage.getTotalPrice()[0], productpage.getTotalPrice()[2]);
	    Assert.assertEquals(productpage.getTotalPrice()[1], productpage.getTotalPrice()[3]);
	}


}
