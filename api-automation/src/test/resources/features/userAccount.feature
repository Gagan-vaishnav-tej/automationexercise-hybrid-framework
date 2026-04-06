Feature: User Account APIs

	Scenario: TC3. Create new User Account
	    When user registers a new account
		Then response status code for registration should be 201
	    And reponse should contains "User created!" message