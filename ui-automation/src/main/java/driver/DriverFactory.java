package driver;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	private static ThreadLocal<WebDriver> driverThread = new ThreadLocal<WebDriver>();
	private static WebDriverWait wait;

	public static WebDriver initDriver(String browser) {
		WebDriver driver;
		switch(browser.toLowerCase()) {
			case "chrome" : WebDriverManager.chromedriver().setup();
							ChromeOptions options = new ChromeOptions();				
							options.addArguments("--disable-notifications");
							options.addArguments("--disable-infobars");
							options.addArguments("--disable-extensions");
							options.addArguments("--disable-popup-blocking");
							options.addArguments("--disable-ads");
							options.addArguments("--disable-features=OverlayScrollbar");
							driver = new ChromeDriver(options);							
							break;
			default : WebDriverManager.edgedriver().setup();
					  	driver = new EdgeDriver();
					  	break;
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driverThread.set(driver);
		return driver;
	}
	
	public static WebDriverWait getWebdrDriverWait(int seconds) {
		wait = new WebDriverWait(driverThread.get(), Duration.ofSeconds(seconds));
		return wait;
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
