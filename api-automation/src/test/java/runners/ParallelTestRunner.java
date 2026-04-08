package runners;

import org.testng.annotations.DataProvider;

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
	        "rerun:target/failed_scenarios.txt",
	    },
	    monochrome = true
	)


public class ParallelTestRunner extends AbstractTestNGCucumberTests{

	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios() {
		return super.scenarios();
	}
}
