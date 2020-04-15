package com.amazon.pageobjects;

public class CheckoutFlowPO {
	public String[] proceedtoCheckout = new String[] {"xpath","//android.widget.Button[@index='0']"};
	public String[] fullName = new String[] {"xpath","//android.widget.EditText[@resource-id='enterAddressFullName']"};
	public String[] phoneNumber = new String[] {"xpath","//android.widget.EditText[@resource-id='enterAddressPhoneNumber']"};
	public String[] addressLine1 = new String[] {"xpath","//android.widget.EditText[@resource-id='enterAddressAddressLine1']"};
	public String[] postalCode = new String[] {"xpath","//android.widget.EditText[@resource-id='enterAddressPostalCode']"};
	public String[] continueButton = new String[] {"xpath","//android.widget.Button[@index='0']"};

	public String[] selectDeliveryAddress = new String[] {"id","a-autoid-0-announce"};
	public String[] selectDeliveryOptions = new String[] {"xpath","//android.widget.Button[@index='0']"};
	public String[] selectPaymentOption = new String[] {"xpath","//android.view.View[@index='1']"};
	public String[] enterNameOnCard = new String[] {"xpath","//android.view.View[@index='8']/android.widget.EditText[@index='1']"};
	public String[] enterCardNumber = new String[] {"xpath","//android.view.View[@index='9']/android.widget.EditText[@index='1']"};
	public String[] selectExpiryMonth = new String[] {"xpath","//android.widget.Spinner[@index='4']"};
	public String[] enterExpiryMonth = new String[] {"xpath","//android.widget.ListView/android.view.View[@text='07']"};
	public String[] selectExpiryYear = new String[] {"xpath","//android.widget.Spinner[@index='6']"};
	public String[] enterExpiryYear = new String[] {"xpath","//android.widget.ListView/android.view.View[@text='2025']"};
	public String[] addYourCard = new String[] {"xpath","//android.widget.Button[@index='0']"};
	public String[] paymentPage = new String[] {"xpath","//android.view.View[@text='Select a payment method']"};
	public String[] placeYourOrder = new String[] {"xpath","//android.widget.Button[@index='0']"};
}
