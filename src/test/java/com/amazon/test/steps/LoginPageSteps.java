package com.amazon.test.steps;

import com.amazon.base.BasePOM;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

public class LoginPageSteps extends BasePOM{
	
	@Then("^User enters email address and click on continue button$")
	public void enterUserNameAndPasswordInLoginPage() {
		loginPage().enterEmailAddress();
		loginPage().clickOnContinueButton();
	}
	
	@And("^User verifies password page$")
	public void userVerifyPasswordPage() {
		loginPage().verifyChangeLoginLink();
	}
	
	@Then("^User enters password and click on Login button")
	public void userEntersPasswordAndClickLoginButton() {
		loginPage().enterPassword();
		loginPage().clickLoginButton();
	}
}
