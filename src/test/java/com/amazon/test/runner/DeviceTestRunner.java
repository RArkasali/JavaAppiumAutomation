package com.amazon.test.runner;

import java.io.File;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/featureFiles"},glue= {"com.amazon.test.steps","com.amazon.utils"},
plugin = { "com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html"}, tags= {"@regression"})

public class DeviceTestRunner{
	
	@AfterClass
    public static void writeExtentReport() {
		System.out.println("started rinning after calss annotation in TestRunner");
        Reporter.loadXMLConfig(new File("config/report.xml"));
    }
}
