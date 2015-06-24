Feature: Delete user
    Addressbook must be able to delete existing users.

    Scenario: Delete existing user
        Given the system is initialized
            And a user with firstname 'Juho' and lastname 'Perälä'
            And another user with firstname 'ToBe' and lastname 'Removed'
        When last user in list is removed
        Then last user should have firstname 'Juho' and lastname 'Perälä'