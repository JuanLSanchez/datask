Feature: Income management

  # List
  Scenario: List income without user
    Given the income resource
    When I make a get request to the URL '/api/income'
    Then http status is forbidden

  Scenario: List income with user
    Given the income resource
    And with the user 'user001' and password 'password'
    When I make a get request to the URL '/api/income'
    Then http status is ok

  # Create
  Scenario: Create income without user
    Given the income resource
    And a good incomeDTO
    When I make a post request to the URL '/api/income'
    Then http status is forbidden

  Scenario: Create income with user
    Given the income resource
    And with the user 'user001' and password 'password'
    And a good incomeDTO
    And count the user's incomes
    When I make a post request to the URL '/api/income'
    Then http status is created
    Then the income is creating
    Then count the user's incomes and it has increse 1

  Scenario: Create income without name with user
    Given the income resource
    And with the user 'user001' and password 'password'
    And a incomeDTO without name
    When I make a post request to the URL '/api/income'
    Then http status is bad request

  # Update
  Scenario: Update income without user
    Given the income resource
    And a good incomeDTO
    When I make a put request to the URL '/api/income/7'
    Then http status is forbidden

  Scenario: Update income with user
    Given the income resource
    And with the user 'user001' and password 'password'
    And a good incomeDTO
    And count the user's incomes
    When I make a put request to the URL '/api/income/7'
    Then http status is ok
    Then the income 7 is updating
    Then count the user's incomes and it has increse 0

  Scenario: Update income with other user
    Given the income resource
    And with the user 'user002' and password 'password'
    And a good incomeDTO
    And count the user's incomes
    When I make a put request to the URL '/api/income/7'
    Then http status is bad request
    Then count the user's incomes and it has increse 0

  Scenario: Update income not created with user
    Given the income resource
    And with the user 'user002' and password 'password'
    And a good incomeDTO
    And count the user's incomes
    When I make a put request to the URL '/api/income/700000'
    Then http status is bad request
    Then count the user's incomes and it has increse 0

  Scenario: Update income without name with user
    Given the income resource
    And with the user 'user001' and password 'password'
    And a incomeDTO without name
    When I make a put request to the URL '/api/income/7'
    Then http status is bad request

  #Delete
  Scenario: Delete income without user
    Given the income resource
    When I make a delete request to the URL '/api/income/7'
    Then http status is forbidden

  Scenario: Delete income with user
    Given the income resource
    And with the user 'user001' and password 'password'
    And count the user's incomes
    When I make a delete request to the URL '/api/income/7'
    Then http status is ok
    Then the income 7 is delete
    Then count the user's incomes and it has increse -1

  Scenario: Delete income with other user
    Given the income resource
    And with the user 'user002' and password 'password'
    And count the user's incomes
    When I make a delete request to the URL '/api/income/7'
    Then http status is bad request
    Then count the user's incomes and it has increse 0

  Scenario: Delete income not created with user
    Given the income resource
    And with the user 'user002' and password 'password'
    And count the user's incomes
    When I make a delete request to the URL '/api/income/700000'
    Then http status is bad request
    Then count the user's incomes and it has increse 0
