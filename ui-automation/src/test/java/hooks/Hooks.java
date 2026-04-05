package hooks;

import org.openqa.selenium.WebDriver;

import config.ConfigReader;
import driver.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.ScreenshotUtil;

public class Hooks {

	private WebDriver driver;
	
	@Before
	public void setUp() {
		driver = DriverFactory.initDriver(ConfigReader.getProperty("browser"));
		driver.get(ConfigReader.getProperty("base.url"));
	}
	
	@After
	public void tearDown(Scenario scenario) {
		if(scenario.isFailed()) {
			byte[] screenshot = ScreenshotUtil.capture(driver);
			scenario.attach(screenshot, "image/png", "Failue Screenshot");
		}
		DriverFactory.quitDriver();
	}
}
