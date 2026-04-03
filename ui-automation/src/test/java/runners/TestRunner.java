package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = "src/test/resources/features",
		glue = {"stepdefinitions", "hooks"},
	    plugin = {
	        "pretty",
	        "html:target/cucumber-reports/report.html",
	        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
	        "json:target/cucumber-reports/report.json",
	    },
	    monochrome = true
	)
public class TestRunner extends AbstractTestNGCucumberTests{

}
