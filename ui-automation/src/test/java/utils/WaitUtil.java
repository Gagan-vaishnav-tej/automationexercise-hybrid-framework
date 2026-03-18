package utils;

import java.time.Duration;

import org.openqa.selenium.support.ui.WebDriverWait;

import driver.DriverFactory;

public class WaitUtil {

	private static WebDriverWait wait;
	
	public static WebDriverWait getWebDriverWait(int seconds) {
		wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(seconds));
		return wait;
	}
}
