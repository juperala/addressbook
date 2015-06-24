package feature.user.delete;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class DeleteUserSteps {

//    @Given("^the system is initialized$")
    public void initializeSystem() {
        System.out.println("Initialized");
    }

    @Given(".+user with firstname '(.+)' and lastname '(.+)'$")
    public void addUser(final String firstName, final String lastName) {
        System.out.println("Add User: " + firstName + lastName);
    }

    @When("^last user in list is removed$")
    public void removeLastUser() {
        System.out.println("Delete last user.");
    }

    @Then("^last user should have firstname '(.+)' and lastname '(.+)'$")
    public void verifyLastUser(final String firstName, final String lastName) {
        System.out.println("Verify last user: " + firstName + lastName);
    }

}