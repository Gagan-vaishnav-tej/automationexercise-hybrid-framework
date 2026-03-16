package driver;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	private static ThreadLocal<WebDriver> driverThread = new ThreadLocal<WebDriver>();

	public static WebDriver initDriver(String browser) {
		WebDriver driver;
		switch(browser.toLowerCase()) {
			case "chrome":
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver(OptionsFactory.buildChromeOptions());							
				break;
			case "edge":
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver(OptionsFactory.buildEdgeOptions());
				break;
			default:
				throw new IllegalArgumentException("Invalid browser");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driverThread.set(driver);
		return driver;
	}
	
	public static WebDriver getDriver() {
		return driverThread.get();
	}
	
	public static void quitDriver() {
		if(driverThread.get() != null) {
			driverThread.get().quit();
			driverThread.remove();
		}
	}
}
