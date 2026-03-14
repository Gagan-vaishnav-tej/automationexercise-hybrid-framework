Feature: User Registration

	@Register @UI
	Scenario: TC1: Register New User and validate

	Given the user is on the home page
	When the user clicks "Signup / Login"
	And the user enters name and email on the sign up form
	And the user submits the sign up form
	And the user enters all required account details
	And the user clicks "Create Account"
	Then the user should see "ACCOUNT CREATED!" message
	When the user clicks "Continue"
	Then the user should be logged in with their name visible