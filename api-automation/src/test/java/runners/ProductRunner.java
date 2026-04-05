package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = "src/test/resources/features",
		glue = {"stepdefinitions"},
		plugin = {
		        "pretty",
		        "html:target/cucumber-reports/report.html",
		        "json:target/cucumber-reports/report.json",
		        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
		    },
		    monochrome = true
		)

public class ProductRunner extends AbstractTestNGCucumberTests{

}
