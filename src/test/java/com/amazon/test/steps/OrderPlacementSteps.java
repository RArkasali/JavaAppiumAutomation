package com.amazon.test.steps;

import com.amazon.base.BasePOM;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

public class OrderPlacementSteps extends BasePOM{
	
	@And("^User click on Proceed to Checkout button cart page$")
	public void clickProceedtoCheckoutButton() {
		orderPlacementPage().verifyProceedtoCheckoutButtonEnabled();
		orderPlacementPage().clickProceedtoCheckoutButton();
	}
	
	@Then("^User selects the delivery address$")
	public void userSelectDeliveryAddress() throws InterruptedException {
		orderPlacementPage().selectDeliveryAddress();
	}
	
	@And("^user continue in delivery options page$")
	public void userContinueDeliveryOptions() {
		orderPlacementPage().continueDeliveryOptions();
	}

	@And("^User enter the payment details and complete the payment$")
	public void userEnterThePaymentDetailsAndCompleteThePayment() {
		orderPlacementPage().addPaymentDetails();
	}
}
