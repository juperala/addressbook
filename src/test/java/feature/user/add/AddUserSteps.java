package feature.user.add;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Feature syntax:
 * Given the system is initialized
 * When new user with firstname '?' and lastname '?' is added
 * Then last user should have firstname '?' and lastname '?'
 */
public class AddUserSteps {

    @Given("^the system is initialized$")
    public void initializeSystem() {
        System.out.println("Initialized");
    }

    @When("^new user with firstname '(.+)' and lastname '(.+)' is added$")
    public void addUser(final String firstName, final String lastName) {
        System.out.println("Add User: " + firstName + lastName);
    }

    @Then("^last user should have firstname '(.+)' and lastname '(.+)'$")
    public void verifyLastUser(final String firstName, final String lastName) {
        System.out.println("Last User: " + firstName + lastName);
    }
}