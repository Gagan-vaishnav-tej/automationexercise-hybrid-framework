package com.automationexercise.hybrid.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"com.automationexercise.hybrid.stepdefinitions","com.automationexercise.hybrid.hooks"},
    plugin = {
        "pretty",
        "html:target/cucumber-reports/report.html",
        "json:target/cucumber-reports/report.json",
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
    },
    monochrome = true
)
public class HybridTestRunner extends AbstractTestNGCucumberTests {

}