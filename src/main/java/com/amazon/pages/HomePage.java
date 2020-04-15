package com.amazon.pages;

import com.amazon.base.BasePOM;
import com.amazon.pageobjects.HomePagePO;

public class HomePage extends BasePOM{

	HomePagePO homePage = new HomePagePO();
	
	public void clickonSignInButton() {
		appHelper().captureScreenShots();
		appHelper().clickByElement(homePage.signInButtion[0], homePage.signInButtion[1]);		
	}
	
	public void isHomePageLanded() {
		appHelper().isElementEnabled(homePage.signInButtion[0], homePage.signInButtion[1]);
	}
	
	public void clickSkipSigninbutton() {
		appHelper().clickByElement(homePage.skipSignInButton[0], homePage.skipSignInButton[1]);
	}
	
}
