Feature: Product APIs

	Scenario: TC1. Get All Products List
		Given user sends GET request to fetch all products
	    Then response status code should be 200
	    And response should match product JSON schema
	  
	Scenario: TC2. Get Single Product Details
		Given user prepares search product form parameter with "Jeans"
	    When user sends POST request to search product API
	    Then response status code should be 200
	    And response should contain searched product details
	    And response should match search product JSON schema