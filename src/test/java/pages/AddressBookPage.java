package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Page object for addressbook web page.
 *
 * @author Juho Perälä
 */
public class AddressBookPage {

    private static final int WAIT_SECS = 10;

    protected WebDriver driver;

    @FindBy(css = ".v-button")
    private WebElement newButton;

    @FindBy(css = ".primary")
    private WebElement saveButton;

    @FindBy(css = "div.v-slot:nth-child(3) > div:nth-child(1)")
    private WebElement cancelButton;

    @FindBy(css = ".v-horizontallayout > div > div > .v-textfield")
    private WebElement searchField;

    @FindBy(xpath = "//*[contains(@class, 'v-formlayout')]/table/tbody/tr[2]/td[3]/input")
    private WebElement fNameField;

    @FindBy(xpath = "//*[contains(@class, 'v-formlayout')]/table/tbody/tr[3]/td[3]/input")
    private WebElement lNameField;

    @FindBy(xpath = "//*[contains(@class, 'v-formlayout')]/table/tbody/tr[4]/td[3]/input")
    private WebElement phoneField;

    @FindBy(xpath = "//*[contains(@class, 'v-formlayout')]/table/tbody/tr[5]/td[3]/input")
    private WebElement emailField;

    @FindBy(xpath = "//*[contains(@class, 'v-formlayout')]/table/tbody/tr[6]/td[3]/div/input")
    private WebElement birthdayField;

    @FindBy(css = ".v-grid-tablewrapper > table > tbody > tr:first-child > td:nth-child(1)")
    private WebElement firstContactFirstName;

    @FindBy(css = ".v-grid-tablewrapper > table > tbody > tr:first-child > td:nth-child(2)")
    private WebElement firstContactLastName;

    @FindBy(css = ".v-grid-tablewrapper > table > thead > tr:first-child > th:first-child")
    private WebElement orderByFirstName;

    public AddressBookPage(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(WAIT_SECS, TimeUnit.SECONDS);
    }

    public void open(String url) {
        driver.get(url);
    }

    public void close() {
        driver.quit();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public AddressBookPage addContact(String fName, String lName) {
        return addContact(fName, lName, false);
    }

    public AddressBookPage addContact(String fName, String lName, boolean cancelOperation) {
        newButton.click();
        fNameField.sendKeys(fName);
        lNameField.sendKeys(lName);
        // TODO: fixed for example
        phoneField.sendKeys("+123-123-1231234");
        // TODO: fixed for example
        emailField.sendKeys(fName + "." + lName + "@test.ts");
        // TODO: fixed for example
        birthdayField.sendKeys("1/1/15");
        if (cancelOperation) {
            cancelButton.click();
        } else {
            saveButton.click();
        }
        return this;
    }

    public AddressBookPage searchContacts(String searchTerm) {
        searchField.sendKeys(searchTerm);
        // TODO: Implement explicit wait to check when search Javascript done
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
        return this;
    }

    public AddressBookPage clearSearch() {
        searchField.clear();
        return this;
    }

    public AddressBookPage updateFirstContact(String firstName, String lastName) {
        return updateFirstContact(firstName, lastName, false);
    }

    public AddressBookPage updateFirstContact(String firstName, String lastName, boolean cancelOperation) {
        firstContactFirstName.click();
        fNameField.clear();
        fNameField.sendKeys(firstName);
        lNameField.clear();
        lNameField.sendKeys(lastName);
        if (cancelOperation) {
            cancelButton.click();
        } else {
            saveButton.click();
        }
        return this;
    }

    public String getFirstNameOfFirstContactInList() {
        return firstContactFirstName.getText();
    }

    public String getLastNameOfFirstContactInList() {
        return firstContactLastName.getText();
    }

    public AddressBookPage orderContactsByFirstName() {
        orderByFirstName.click();
        return this;
    }

    public boolean isContactPresent(String firstName, String lastName) {
        List<WebElement> contacts = driver.findElements(By.cssSelector(".v-grid-tablewrapper > table > tbody > tr"));
        Iterator<WebElement> iter = contacts.iterator();

        while (iter.hasNext()) {
            WebElement contact = iter.next();
            if (firstName.equals(contact.findElement(By.cssSelector("td:nth-child(1)")).getText())) {
                if (lastName.equals(contact.findElement(By.cssSelector("td:nth-child(2)")).getText())) {
                    // contact match firstname + lastname, consider as match in this example
                    return true;
                }
            }
        }
        // no matching contact found
        return false;
    }

    public int getContactCount() {
        return driver.findElements(By.cssSelector(".v-grid-tablewrapper > table > tbody > tr")).size();
    }
}
