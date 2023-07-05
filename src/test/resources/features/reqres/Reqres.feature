Feature: API Automation Testing ALTA QE Batch 11
  @Latihan
  Scenario Outline: Get list users with valid parameter page
    Given Get list user with valid parameter page <page>
    When Send request get list users
    Then Status code should be 200 OK
    And Response body page value should be <page>
    Examples:
      |page  |
      |1     |
      |2     |
  @Latihan
  Scenario: Post create new user with valid JSON
    Given Post create user with valid json
    When Send request post create user
    Then Status code should be 201 created
    And Response body name was "Wisnu Munawar" and job was "QA Engineer"