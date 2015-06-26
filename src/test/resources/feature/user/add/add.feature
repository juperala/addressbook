Feature: Add user
    Addressbook must be able to support adding new users.

    Scenario: Add new user
        Given the system is initialized
        When new user with firstname 'Teppo' and lastname 'Testaaja' is added
        Then first user should have firstname 'Teppo'