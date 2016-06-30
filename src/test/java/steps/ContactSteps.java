package steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.MarionetteDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.AddressBookPage;

/**
 * Definition of Cucumber test steps.
 * Accessing addressbook webpage using Selenium is separated as own page object for clarity.
 *
 * @author Juho Perälä
 */
public class ContactSteps {
    private static final Logger LOG = LoggerFactory.getLogger(ContactSteps.class);
    private static final String BASE_URL = "http://localhost:8080/";

    private AddressBookPage page;

    @Before
    public void beforeScenario() {
        WebDriver driver;
        String browser = System.getProperty("browser", "firefox");
        switch(browser.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "marionette":
                driver = new MarionetteDriver();
                break;
            case "firefox":
            default:
                driver = new FirefoxDriver();
        }
        page = PageFactory.initElements(driver, AddressBookPage.class);
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

    @Given(".+person with firstname '(.+)' and lastname '(.+)'$")
    public void initContact(final String firstName, final String lastName) {
        LOG.debug("Adding default contact {} {}.", firstName, lastName);
        page.addContact(firstName, lastName);
    }

    @When("^new person with firstname '(.+)' and lastname '(.+)' is added$")
    public void addContact(final String firstName, final String lastName) {
        LOG.debug("Adding contact {} {}.", firstName, lastName);
        page.addContact(firstName, lastName);
    }

    @When("^adding new person with firstname '(.+)' and lastname '(.+)' is started but operation is canceled$")
    public void addNewContactButCancelProcess(final String firstName, final String lastName) {
        LOG.debug("Start adding contact {} {} but cancel operation.", firstName, lastName);
        page.addContact(firstName, lastName, true);
    }

    @When("^first person in contacts is updated with firstname '(.+)' and lastname '(.+)'$")
    public void updateFirstContact(final String firstName, final String lastName) {
        LOG.debug("Updating first contact to {} {}.", firstName, lastName);
        page.updateFirstContact(firstName, lastName);
    }

    @When("^updating first person in contacts with firstname '(.+)' and lastname '(.+)' is started but operation is canceled$")
    public void updateFirstContactButCancelProcess(final String firstName, final String lastName) {
        LOG.debug("Start updating first contact to {} {} but cancel operation.", firstName, lastName);
        page.updateFirstContact(firstName, lastName, true);
    }

    @When("^contact list is ordered$")
    public void orderContacts() {
        LOG.debug("Ordering contacts.");
        page.orderContactsByFirstName();
    }

    @When("^search is cleared$")
    public void clearSearch() {
        LOG.debug("Clearing search.");
        page.clearSearch();
    }

    @When("^search '(.+)' is made$")
    public void orderContacts(final String search) {
        LOG.debug("Searching contacts with query {}", search);
        page.searchContacts(search);
    }

    @Then("^first person in contacts should have firstname '(.+)' and lastname '(.+)'$")
    public void verifyFirstContact(final String firstName, final String lastName) {
        LOG.debug("Verifying first contact in list.");
        Assert.assertEquals("First contact in addressbook has wrong firstname.", firstName, page.getFirstNameOfFirstContactInList());
        Assert.assertEquals("First contact in addressbook has wrong lastname.", lastName, page.getLastNameOfFirstContactInList());
    }

    @Then("^last person in contacts should have firstname '(.+)' and lastname '(.+)'$")
    public void verifyLastContact(final String firstName, final String lastName) {
        LOG.debug("Verifying last contact in list.");
        Assert.assertEquals("Last contact in addressbook has wrong firstname.", firstName, page.getFirstNameOfFirstContactInList());
        Assert.assertEquals("Last contact in addressbook has wrong lastname.", lastName, page.getLastNameOfFirstContactInList());
    }

    @Then("^number of contacts is (\\d+)$")
    public void verifyContactCount(final int count) throws InterruptedException {
        LOG.debug("Verifying contacts count, expected {}.", count);
        Assert.assertEquals("Contact count mismatch.", count, page.getContactCount());
    }

    @Then("^person with firstname '(.+)' and lastname '(.+)' exist in contacts$")
    public void verifyContactExists(final String firstName, final String lastName) throws InterruptedException {
        LOG.debug("Verifying person {} {} present in the contacts", firstName, lastName);
        Assert.assertTrue("Person not found in contacts.", page.isContactPresent(firstName, lastName));
    }

    @Then("^person with firstname '(.+)' and lastname '(.+)' does not exist in contacts$")
    public void verifyContactDoesNotExist(final String firstName, final String lastName) throws InterruptedException {
        LOG.debug("Verifying person {} {} not present in the contacts", firstName, lastName);
        Assert.assertFalse("Person was found in contacts.", page.isContactPresent(firstName, lastName));
    }
}