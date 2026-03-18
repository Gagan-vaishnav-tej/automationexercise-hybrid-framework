Feature: User Registration

	@Register @UI
	Scenario: TC1: Register New User and validate

	Given the user is on the home page
	Then the home page should be visible successfully	
	When the user clicks "Signup / Login"
	Then the user should see "New User Signup!" message	
	When the user enters name and email on the sign up form
	And the user clicks "Signup"
	Then the user should see "ENTER ACCOUNT INFORMATION" message	
	When the user enters all required account details
	And the user clicks "Create Account"
	Then the user should see "ACCOUNT CREATED!" message	
	When the user clicks "Continue"
	Then the user should be logged in with their name visible	
	When the user clicks "Delete Account"
    Then the user should see "ACCOUNT DELETED!" message
    And the user clicks "Continue"