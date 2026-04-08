Feature: User Account APIs

	Scenario: TC3. Create new User Account
	    When user registers a new account
		Then response status code for registration should be 201
	    And response should contains "User created!" message
	    
	@Delete
  	Scenario: TC4. Delete User Account (Positive)
	    When user deletes the account
	    Then delete account response status code should be 200
	    And delete account response should contain "Account deleted!" message
	  
	@Update
	Scenario:  TC 5. Update User Account
		When user sends put request to update the account
		Then response code for registration should be 200
		And response should contain "User updated!" message
