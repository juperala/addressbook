Feature: Update user
    Addressbook must be able to update existing users.

    Scenario: Update existing user
        Given the system is initialized
            And a user with firstname 'Koe' and lastname 'Ihminen'
            And another user with firstname 'Veikko' and lastname 'Varamies'
        When first user in contacts is updated with firstname 'Teppo' and lastname 'Testaaja'
            And contact list is ordered
        Then first user in contacts should have firstname firstname 'Teppo' and lastname 'Testaaja'
            And last user in contacts should have firstname firstname 'Koe' and lastname 'Ihminen'
            And number of users is 2