Feature: Access Amazon APP and Login  
	Verify Login is success in amazon APP
	
	@regression
	Scenario Outline: Access and Login to Amazon App
	  Given user tap on Amazon APP on device
	  When the app opens verify the app loaded successfully
	  Then the user tap on SignIn button
	  Then User enters email address and click on continue button
	  And User verifies password page
	  Then User enters password and click on Login button
	  Then User verifies search bar appearing in homepage
	  And search for a <searchKeyword> in search bar
	  Then user selects the <searchKeyword> in the list
	  And User select the <productName> from the results page
	  And User save the product details for validate
	  Then User clicks on add to cart button
	  And verify the product price and name in cart page
	  Then User click on Proceed to Checkout button cart page
	  Then User selects the delivery address
	  And user continue in delivery options page
	  Then User enter the payment details and complete the payment

	  Examples:
		  |searchKeyword|productName|
		  |65-inch TV|65"|
