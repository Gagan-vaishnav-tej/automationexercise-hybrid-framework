Feature: User Registration

	@Register @UI
	Scenario: TC1: Register New User and validate

	Given the user is on the home page	
	When the user clicks Signup or Login button
	Then the user should see New User Signup! message	
	When the user enters name and email on the sign up form
	And the user clicks Signup button
	Then the user should see ENTER ACCOUNT INFORMATION message	
	When the user enters all required account details
	And the user clicks Create Account button
	Then the user should see ACCOUNT CREATED! message	
	When the user clicks Continue button
	Then the user should be logged in with their name visible
	When the user clicks Delete Account button
	And the user clicks Continue button