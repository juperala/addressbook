Feature: Search person in contacts
    Addressbook must be able to allow searching of contacts.

    Scenario: Search
        Given the system is initialized
            And a person with firstname 'Osuma' and lastname 'Haku'
            And another person with firstname 'Huti' and lastname 'Haku'
        When search 'Osuma' is made
        Then person with firstname 'Osuma' and lastname 'Haku' exist in contacts
            And number of contacts is 1
        When search is cleared
            And search 'Haku' is made
        Then number of contacts is 2