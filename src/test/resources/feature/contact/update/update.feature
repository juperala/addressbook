Feature: Update person in contacts
    Addressbook must be able to update existing contacts.

    Scenario: Update existing person
        Given the system is initialized
            And a person with firstname 'Veikko' and lastname 'Varamies'
        When first person in contacts is updated with firstname 'Veikko' and lastname 'Varamies-Patela'
        Then person with firstname 'Veikko' and lastname 'Varamies-Patela' exist in contacts
            And person with firstname 'Veikko' and lastname 'Varamies' does not exist in contacts

    Scenario: Cancel update
        Given the system is initialized
          And a person with firstname 'Keijo' and lastname 'Kekkonen'
        When updating first person in contacts with firstname 'Keijo' and lastname 'Kekkonen-Patela' is started but operation is canceled
        Then person with firstname 'Keijo' and lastname 'Kekkonen' exist in contacts
          And person with firstname 'Keijo' and lastname 'Kekkonen-Patela' does not exist in contacts