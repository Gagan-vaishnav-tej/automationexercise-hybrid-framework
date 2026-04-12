package com.automationexercise.crosslayer.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
			features = "@target/failed_scenarios.txt",
			glue = {"com.automationexercise.crosslayer.stepdefinitions"},
		    plugin = {
		        "pretty",
		        "html:target/cucumber-reports/report.html",
		        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
		        "json:target/cucumber-reports/report.json",
		        "rerun:target/failed_scenarios.txt",
		    },
		    monochrome = true
		)

public class FailedTestRunner extends AbstractTestNGCucumberTests{

}
