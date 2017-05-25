Feature: Account management

  # Get
  Scenario: Get account without user
    Given the account resource
    When I make a get request to the URL '/api/account'
    Then http status is forbidden

  Scenario: Get account with a user
    Given the account resource
    And with the user 'user001' and password 'password'
    When I make a get request to the URL '/api/account'
    Then http status is ok
    Then the account is from the user