package com.amazon.pages;

import com.amazon.base.BasePOM;
import com.amazon.constants.PropertyConstants;
import com.amazon.pageobjects.CheckoutFlowPO;
import com.amazon.utils.ReadPropertiesFile;

public class OrderPlacementPage extends BasePOM{
	
	CheckoutFlowPO checkoutFlow = new CheckoutFlowPO();
	
	public void verifyProceedtoCheckoutButtonEnabled() {
		appHelper().isElementEnabled(checkoutFlow.proceedtoCheckout[0], checkoutFlow.proceedtoCheckout[1]);
	}

	public void clickProceedtoCheckoutButton() {
		appHelper().clickByElement(checkoutFlow.proceedtoCheckout[0], checkoutFlow.proceedtoCheckout[1]);
	}
	
	public void selectDeliveryAddress() throws InterruptedException {
		if(appHelper().isElementDisplayed(checkoutFlow.fullName[0], checkoutFlow.fullName[1])) {
			enterCustomerData();
			Thread.sleep(5000);
			appHelper().clickByElement(checkoutFlow.continueButton[0], checkoutFlow.continueButton[1]);
		}else {
			appHelper().clickByElement(checkoutFlow.selectDeliveryAddress[0], checkoutFlow.selectDeliveryAddress[1]);
		}
	}
	
	public void continueDeliveryOptions() {
		if(appHelper().isElementDisplayed(checkoutFlow.selectDeliveryOptions[0], checkoutFlow.selectDeliveryOptions[1]))
			appHelper().clickByElement(checkoutFlow.selectDeliveryOptions[0], checkoutFlow.selectDeliveryOptions[1]);
	}

	public void enterCustomerData() {
		appHelper().enterTextByElement(checkoutFlow.fullName[0], checkoutFlow.fullName[1], ReadPropertiesFile.GetProperty(PropertyConstants.FULLNAME));
		appHelper().enterTextByElement(checkoutFlow.phoneNumber[0], checkoutFlow.phoneNumber[1], ReadPropertiesFile.GetProperty(PropertyConstants.PHONENUMBER));
		appHelper().enterTextByElement(checkoutFlow.addressLine1[0], checkoutFlow.addressLine1[1], ReadPropertiesFile.GetProperty(PropertyConstants.ADDRESS));
		appHelper().enterTextByElement(checkoutFlow.postalCode[0], checkoutFlow.postalCode[1], ReadPropertiesFile.GetProperty(PropertyConstants.POSTALCODE));
	}

	public void addPaymentDetails() {
		appHelper().clickByElement(checkoutFlow.selectPaymentOption[0], checkoutFlow.selectPaymentOption[1]);
		appHelper().enterTextByElement(checkoutFlow.enterNameOnCard[0], checkoutFlow.enterNameOnCard[1], ReadPropertiesFile.GetProperty(PropertyConstants.CARDNAME));
		appHelper().enterTextByElement(checkoutFlow.enterCardNumber[0], checkoutFlow.enterCardNumber[1], ReadPropertiesFile.GetProperty(PropertyConstants.CARDNUMBER));
		appHelper().clickByElement(checkoutFlow.selectExpiryMonth[0], checkoutFlow.selectExpiryMonth[1]);
		appHelper().clickByElement(checkoutFlow.enterExpiryMonth[0], checkoutFlow.enterExpiryMonth[1]);
		appHelper().clickByElement(checkoutFlow.selectExpiryYear[0], checkoutFlow.selectExpiryYear[1]);
		appHelper().clickByElement(checkoutFlow.enterExpiryYear[0], checkoutFlow.enterExpiryYear[1]);
		appHelper().clickByElement(checkoutFlow.addYourCard[0], checkoutFlow.addYourCard[1]);
		appHelper().isElementEnabled(checkoutFlow.paymentPage[0], checkoutFlow.paymentPage[1]);
		appHelper().clickByElement(checkoutFlow.continueButton[0], checkoutFlow.continueButton[1]);
		appHelper().clickByElement(checkoutFlow.placeYourOrder[0], checkoutFlow.placeYourOrder[1]);
	}
}
