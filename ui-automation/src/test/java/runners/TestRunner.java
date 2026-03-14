package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = "src/test/resources/features",
		glue = {"stepdefinition", "src/main/java/hooks"},
		tags = "@Register",
	    plugin = {
	        "pretty",
	        "html:target/cucumber-reports/register.html",
	        "json:target/cucumber-reports/register.json",
	    },
	    monochrome = true
	)

public class TestRunner extends AbstractTestNGCucumberTests{

}
