package stepdefinitions;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import driver.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import logger.LogsFactory;
import pages.CartPage;
import pages.HomePage;
import pages.ProductPage;

public class RemoveProductSteps {

    private WebDriver driver;
    private HomePage homePage;
    private ProductPage productPage;
    private CartPage cartPage;
    private String removedProductXpath;
    private String removedProductName;
    
    private Logger logger = LogsFactory.getLogger(RemoveProductSteps.class);

    @Given("the user is on home page")
    public void userOnHomePage() {
        driver = DriverFactory.getDriver();

        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);

        logger.info("User is on home page");
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible");
    }

    @And("the user has products in the cart")
    public void userHasProductsInCart() {
        homePage.clickProduct();
        productPage.addFirstProductToCart();
        productPage.clickOnContinueButton();
        logger.info("User adds product to cart");
    }

    @When("the user clicks on {string} button")
    public void clickCartButton(String button) {
        if (button.equalsIgnoreCase("Cart")) {
            homePage.clickCart();
        }
        logger.info("User clicked cart button");
    }

    @Then("the cart page should be displayed")
    public void verifyCartPage() {
        Assert.assertTrue(cartPage.isCartPageDisplayed(), "Cart page is not displayed");
        logger.info("Cart page is displayed");
    }

    @When("the user removes the product from the cart")
    public void removeProduct() {
        removedProductName = cartPage.getFirstProductName();
        removedProductXpath = cartPage.getProductXpath(removedProductName);

        logger.info("Product to be removed: " + removedProductName);

        cartPage.removeProductFromCart();
        cartPage.waitUntilProductIsRemoved(removedProductXpath);

        logger.info("User removed product from the cart");
    }

    @Then("the product should be removed from the cart")
    public void verifyRemoved() {
        Assert.assertFalse(
                cartPage.isProductVisibleInCart(removedProductXpath),
                "Product is still visible in the cart: " + removedProductName
        );
        logger.info("Verified product is not visible in cart: " + removedProductName);
    }
}