package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Definition of Cucumber test steps.
 * @author Juho Perälä
 */
public class UserSteps {

    @Given("^the system is initialized$")
    public void initializeSystem() {
        System.out.println("Initialized");
    }

    @Given(".+user with firstname '(.+)' and lastname '(.+)'$")
    public void initUser(final String firstName, final String lastName) {
        System.out.println("Add User: " + firstName + lastName);
    }

    @When("^last user in list is removed$")
    public void removeLastUser() {
        System.out.println("Delete last user.");
    }

    @When("^new user with firstname '(.+)' and lastname '(.+)' is added$")
    public void addUser(final String firstName, final String lastName) {
        System.out.println("Add User: " + firstName + lastName);
    }

    @Then("^last user should have firstname '(.+)' and lastname '(.+)'$")
    public void verifyLastUser(final String firstName, final String lastName) {
        System.out.println("Verify last user: " + firstName + lastName);
    }
}