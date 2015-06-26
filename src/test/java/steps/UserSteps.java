package steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.AddressBookPage;

import java.util.concurrent.TimeUnit;

/**
 * Definition of Cucumber test steps. Accessing addressbook webpage using Selenium is separated as own page object
 * for clarity.
 *
 * @author Juho Perälä
 */
public class UserSteps {
    private static final Logger LOG = LoggerFactory.getLogger(UserSteps.class);
    private static final String BASE_URL = "http://localhost:8080/";

    private AddressBookPage page;

    @Before
    public void beforeScenario() {
        page = PageFactory.initElements(new FirefoxDriver(), AddressBookPage.class);
    }

    @After
    public void afterScenario() {
        page.close();
    }

    @Given("^the system is initialized$")
    public void initializeSystem() {
        LOG.debug("Initializing system.");
        page.open("http://localhost:8080/");
    }

    @Given(".+user with firstname '(.+)' and lastname '(.+)'$")
    public void initUser(final String firstName, final String lastName) {
        LOG.debug("Adding default user {} {}.", firstName, lastName);
        page.addUser(firstName, lastName);
    }

    @When("^new user with firstname '(.+)' and lastname '(.+)' is added$")
    public void addUser(final String firstName, final String lastName) {
        LOG.debug("Adding user {} {}.", firstName, lastName);
        page.addUser(firstName, lastName);
    }

    @When("^first user in contacts is updated with firstname '(.+)' and lastname '(.+)'$")
    public void updateFirstUser(final String firstName, final String lastName) {
        LOG.debug("Updating user to {} {}.", firstName, lastName);
        page.updateFirstContact(firstName, lastName);
    }

    @When("^last user in contacts is updated with firstname '(.+)' and lastname '(.+)'$")
    public void updateLastUser(final String firstName, final String lastName) {
        LOG.debug("Updating user to {} {}.", firstName, lastName);
        page.updateLastContact(firstName, lastName);
    }

    @When("^contact list is ordered$")
    public void orderContacts() {
        LOG.debug("Ordering contacts.");
        page.orderByFirstName();
    }

    @Then("^first user in contacts should have firstname '(.+)' and lastname '(.+)$")
    public void verifyFirstUser(final String firstName, final String lastName) {
        LOG.debug("Verifying first user.");
        Assert.assertEquals("First contact in addressbook has wrong firstname.", firstName, page.getFirstNameOfFirstContactInList());
        Assert.assertEquals("First contact in addressbook has wrong lastname.", lastName, page.getLastNameOfFirstContactInList());
    }

    @Then("^last user in contacts should have firstname '(.+)' and lastname '(.+)$")
    public void verifyLastUser(final String firstName, final String lastName) {
        LOG.debug("Verifying last user");
        Assert.assertEquals("Last contact in addressbook has wrong firstname.", firstName, page.getFirstNameOfFirstContactInList());
        Assert.assertEquals("Last contact in addressbook has wrong lastname.", lastName, page.getLastNameOfFirstContactInList());
    }

    @Then("^number of users is '(\\d+)'$")
    public void verifyUserCount(final int count) {
        LOG.debug("Verifying user count, expected={}.", count);
        Assert.assertEquals("User count mismatch.", count, page.getContactCount());
    }


}