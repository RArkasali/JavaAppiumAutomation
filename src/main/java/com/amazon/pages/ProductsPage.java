package com.amazon.pages;

import com.amazon.base.BasePOM;
import com.amazon.pageobjects.ProductsPO;
import com.amazon.utils.AssertHelper;
import com.amazon.utils.DeviceActionUtils;

public class ProductsPage extends BasePOM{
	
	ProductsPO products = new ProductsPO();
	DeviceActionUtils deviceActionUtils = new DeviceActionUtils(this.getDriver());
	String productName, price;

	public void enterProductinSearchBar(String product) {
		appHelper().clickByElement(products.searchTextBox[0], products.searchTextBox[1]);
		appHelper().enterTextWithKeyPad(products.searchTextBox[0], products.searchTextBox[1], product);
	}
	
	public void getListofProducts() {
		appHelper().verifySizeofSearResults(products.searchList[0], products.searchList[1]);
	}
	
	public void accessFirstProductInSearchResults() {
		appHelper().clickByElement(products.firstSearchOption[0], products.firstSearchOption[1]);
	}
	
	public void selectGetProductNamefromResults(String productName) {
		appHelper().selectProductByName(products.productIndex[0], products.productIndex[1], productName);
	}
	
	public void verifySearchBarInHomePage() {
		appHelper().isElementEnabled(products.searchTextBox[0], products.searchTextBox[1]);
	}
	
	public void clickOnAddtoCartButton() {
		appHelper().scrollToElement(products.addToCartButton[0], products.addToCartButton[1]);
		deviceActionUtils.verticalSwipeByPercentages(0.5, 0.4, 0.2);
		appHelper().clickByElement(products.addToCartButton[0], products.addToCartButton[1]);
	}
	
	public void navigateToSoppingCartPage() {
		appHelper().clickByElement(products.miniCart[0], products.miniCart[1]);
	}

	public void getProductTitle(){
		productName = appHelper().getTextByLocator(products.productTitle[0], products.productTitle[1]);
		System.out.println("Product name"+ productName);
	}
	
	public void getProductPrice() {
		price = appHelper().getTextByLocator(products.priceToPay[0], products.priceToPay[1]);
		System.out.println("Price"+ price);
	}
	
	public void verifyPriceAndProductName() {
		String content = appHelper().getTextByLocator(products.cartPageContent[0], products.cartPageContent[1]);
		if(content!=null && !content.isEmpty()) {
			AssertHelper.assertTrue(content.contains(price));
			AssertHelper.assertTrue(content.contains(productName));
		}
	}
}
