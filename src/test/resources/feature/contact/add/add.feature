Feature: Add person to contacts
    Addressbook must be able to support adding persons in contact list.

    Scenario: Add new person
        Given the system is initialized
        When new person with firstname 'Teppo' and lastname 'Testaaja' is added
        Then person with firstname 'Teppo' and lastname 'Testaaja' exist in contacts

    Scenario: Cancel addition
        Given the system is initialized
        When adding new person with firstname 'Lasse' and lastname 'Laaka' is started but operation is canceled
        Then person with firstname 'Lasse' and lastname 'Laaka' does not exist in contacts