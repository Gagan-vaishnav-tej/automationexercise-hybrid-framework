package com.automationexercise.crosslayer.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"com.automationexercise.crosslayer.stepdefinitions","com.automationexercise.crosslayer.hooks"},
    plugin = {
        "pretty",
        "html:target/cucumber-reports/report.html",
        "json:target/cucumber-reports/report.json",
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
        "rerun:target/failed_scenarios.txt",
    },
    monochrome = true
)
public class CrossLayerTestRunner extends AbstractTestNGCucumberTests {

}