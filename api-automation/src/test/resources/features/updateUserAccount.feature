Feature: Update UserAccount APIs
	
	@update
	Scenario:  TC 5. Update User Account
		When user sends put request to update the account
		Then response code for registration should be 200
		And response should contain "User updated!" message