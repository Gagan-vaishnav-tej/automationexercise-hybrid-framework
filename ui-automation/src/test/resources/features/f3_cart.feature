Feature: Add and Remove Products from and to the Cart

	@Smoke @UI
	Scenario: TC4: Add Product to the Cart and validate 
	Given the user is on the home page
	When the user Click Products button
	And  Hover over first product and click Add to cart 
	And Click Continue Shopping button
	And  user Hover over second product and click Add to cart
	And Click view Cart button
	Then user verify both products are added to Cart
	And user should verify their prices, quantity and total price
	
	@Regression @UI
	Scenario: TC5: Remove Item from cart and validate
	Given the user is on home page
	And the user has products in the cart
	When the user clicks on "Cart" button
	Then the cart page should be displayed
	When the user removes the product from the cart
	Then the product should be removed from the cart
	
	