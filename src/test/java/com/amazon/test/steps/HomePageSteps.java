package com.amazon.test.steps;

import com.amazon.base.BasePOM;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class HomePageSteps extends BasePOM{
	
	@Given("^user tap on Amazon APP on device$")
	public void userAccessedApp() {
		System.out.println("Amazon App is loading");
		getDriver();
	}
	
	@When("^the app opens verify the app loaded successfully$")
	public void validateHomePage() {
		System.out.println("Login Page loading in progress");
		homePage().isHomePageLanded();
	}
	
	@Then("^the user tap on SignIn button$")
	public void userTapsonSigninButton() {
		System.out.println("user clicks on signin button");
		homePage().clickonSignInButton();
	}
	
	@Then("^user tap on skip sign in button$")
	public void skipSignInButton() {
		homePage().clickSkipSigninbutton();
	}
}

	