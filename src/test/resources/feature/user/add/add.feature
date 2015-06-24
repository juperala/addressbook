Feature: Add user
    Addressbook must be able to support adding new users.

    Scenario: Add new user
        Given the system is initialized
        When new user with firstname 'Juho' and lastname 'Perälä' is added
        Then last user should have firstname 'Juho' and lastname 'Perälä'