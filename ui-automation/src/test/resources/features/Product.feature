Feature:  products in cart
	
	@AddProduct @UI
	Scenario: TC4: Add Product to the Cart and validate 
	Given the user is on the home page
	When the user Click Products button
	And  Hover over first product and click Add to cart 
	And Click Continue Shopping button
	And  user Hover over second product and click Add to cart
	And Click view Cart button
	Then user verify both products are added to Cart
	And user should verify their prices, quantity and total price