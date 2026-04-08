Feature: Delete User Account APIs

  @delete
  Scenario: TC4. Delete User Account (Positive)
    When user deletes the account
    Then delete account response status code should be 200
    And delete account response should contain "Account deleted!" message


  @delete-negative
 Scenario: TC6. Delete User Account (Negative - Invalid Credentials)
  Given user tries to delete with invalid credentials
  When user tries to delete account with invalid credentials
  Then delete account response status code should be 200
  And delete account response should contain "Account not found!" message