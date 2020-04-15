package com.amazon.pageobjects;

public class LoginPagePO {
	
	public String[] emailID = new String[] {"xpath","//android.widget.EditText[@resource-id='ap_email_login']"};
	public String[] continueButton = new String[] {"xpath","//android.widget.Button[@resource-id='continue']"};
	public String[] password = new String[] {"xpath","//android.widget.EditText[@resource-id='ap_password']"};
	public String[] userName = new String[] {"xpath","//android.view.View[@index='3']"};
	public String[] loginButton = new String[] {"xpath","//android.widget.Button[@resource-id='signInSubmit']"};
	public String[] changeLink = new String[] {"xpath","//android.view.View[@resource-id='ap_change_login_claim']"};

}
