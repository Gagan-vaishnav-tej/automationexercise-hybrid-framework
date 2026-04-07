Feature: Remove product in cart
<<<<<<< HEAD
@RemoveProduct @UI
Scenario: TC5: Remove Item from cart and validate
Given the user is on home page
And the user has products in the cart
When the user clicks on "Cart" button
Then the cart page should be displayed
When the user removes the product from the cart
Then the product should be removed from the cart
=======
	
	@RemoveProduct @UI
	Scenario: TC5: Remove Item from cart and validate
	Given the user is on home page
	And the user has products in the cart
	When the user clicks on "Cart" button
	Then the cart page should be displayed
	When the user removes the product from the cart
	Then the product should be removed from the cart
>>>>>>> 96c8351583b95977e30499e9dff4b17c06b92e1c
