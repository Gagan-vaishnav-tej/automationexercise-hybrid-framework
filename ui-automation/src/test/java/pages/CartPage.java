package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By deleteButton = By.cssSelector("a.cart_quantity_delete");
    private By cartTable = By.id("cart_info_table");
    private By firstProductName = By.xpath("(//td[@class='cart_description']//a)[1]");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public boolean isCartPageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cartTable)).isDisplayed();
    }

    public String getFirstProductName() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(firstProductName))
                   .getText()
                   .trim();
    }

    public String getProductXpath(String productName) {
        return "//td[@class='cart_description']//a[normalize-space()='" + productName + "']";
    }

    public void removeProductFromCart() {
        wait.until(ExpectedConditions.elementToBeClickable(deleteButton)).click();
    }

    public void waitUntilProductIsRemoved(String productXpath) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(productXpath)));
    }

    public boolean isProductVisibleInCart(String productXpath) {
        try {
            List<WebElement> products = driver.findElements(By.xpath(productXpath));
            return !products.isEmpty() && products.get(0).isDisplayed();
        } catch (StaleElementReferenceException e) {
            return false;
        }
    }
}