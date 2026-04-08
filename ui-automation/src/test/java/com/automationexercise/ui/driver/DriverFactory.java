package com.automationexercise.ui.driver;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	private static ThreadLocal<WebDriver> driverThread = new ThreadLocal<WebDriver>();

	public static WebDriver initDriver(String browser) {
		
		Logger.getLogger("org.openqa.selenium").setLevel(Level.SEVERE);
        Logger.getLogger("io.github.bonigarcia").setLevel(Level.SEVERE);
        
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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driverThread.set(driver);
		return driver;
	}
	
	public static WebDriver getDriver() {
	    if (driverThread.get() == null) {
	        throw new RuntimeException("Driver is not initialized. Check Hooks.");
	    }
	    return driverThread.get();
	}
	
	public static void quitDriver() {
		if(driverThread.get() != null) {
			driverThread.get().quit();
			driverThread.remove();
		}
	}
}

