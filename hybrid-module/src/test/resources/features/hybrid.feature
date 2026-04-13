Feature: Hybrid Testing (API + UI )

	@Hybrid
	Scenario: TC1. Create new User Account Via API and validate Login Via UI
	    When user registers a new account via API 
		Then response status code for registration via API should be 201
	    And retrieved response should contains "User created!" message
	    And retrieved response should match create user response JSON schema
	    
	    Then the email and password of the user registered via API is retrieved
	    Given the registered user is on the home page
		When the registered user clicks Signup or Login button
		Then the registered user should see Login to your account message
		When the registered user enters registered email and password in login form
		And the registered user clicks Login button
		Then the registered user should see logged is as username
		When the registered user clicks Delete Account button
		And the registered user clicks Continue button
	    
