Feature: User Registration
  As a new user
  I want to register in the system
  So that I can access the platform

  Scenario: Successful user registration
    Given I have a new user with username "testuser" and password "password123"
    When I submit the registration request
    Then the registration should be successful
    And I should receive a JWT token