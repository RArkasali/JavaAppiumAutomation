package com.amazon.pages;

import com.amazon.base.BasePOM;
import com.amazon.constants.PropertyConstants;
import com.amazon.pageobjects.LoginPagePO;
import com.amazon.utils.AssertHelper;
import com.amazon.utils.ReadPropertiesFile;

public class LoginPage extends BasePOM{
	
	LoginPagePO loginPage = new LoginPagePO();
	
	public void enterEmailAddress() {
		appHelper().enterTextWithKeyPad(loginPage.emailID[0],loginPage.emailID[1],ReadPropertiesFile.GetProperty(PropertyConstants.USERNAME));
	}
	
	public void enterPassword() {
		appHelper().enterTextWithKeyPad(loginPage.password[0],loginPage.password[1],ReadPropertiesFile.GetProperty(PropertyConstants.PASSWORD));
	}
	
	public void clickOnContinueButton() {
		appHelper().clickByElement(loginPage.continueButton[0], loginPage.continueButton[1]);
	}
	
	public void verifyUserNameVisibility() {
		boolean assertValue = (appHelper().getTextByLocator(loginPage.userName[0], loginPage.userName[1]).equals(ReadPropertiesFile.GetProperty(PropertyConstants.USERNAME)));
		AssertHelper.assertTrue(assertValue);
	}
	
	public void clickLoginButton() {
		appHelper().clickByElement(loginPage.loginButton[0], loginPage.loginButton[1]);
	}
	
	
	public void verifyChangeLoginLink() {
		appHelper().isElementEnabled(loginPage.changeLink[0], loginPage.changeLink[1]);
	}
}
