Feature:Negative Validation

  	@Regression @API
	Scenario: TC6. Delete User Account (Negative - Invalid Credentials)
	  Given user tries to delete with invalid credentials
	  When user tries to delete account with invalid credentials
	  Then delete account response status code should be 404
	  And delete account response should contain "Account not found!" message
	  And response should match delete user response JSON schema
	  
	  