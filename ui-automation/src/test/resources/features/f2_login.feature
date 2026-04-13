Feature: User Login
	
	@Smoke @UI
	Scenario: TC2: Valid User Login
	Given the user is on the home page
	When the user clicks Signup or Login button
	Then the user should see Login to your account message
	When the user enters valid email and valid password in login form
	And the user clicks Login button
	Then the user should see logged is as username
	
	@Regression @UI
	Scenario: TC3: Invalid User Login with invalid email
	Given the user is on the home page
	When the user clicks Signup or Login button
	Then the user should see Login to your account message
	When the user enters invalid email and password in login form
	And the user clicks Login button
	Then the user should see "Your email or password is incorrect!"
