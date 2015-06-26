package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page object for addressbook web page.
 *
 * @author Juho Perälä
 */
public class AddressBookPage {

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

    @FindBy(css = ".v-grid-tablewrapper > table > tbody > tr:last-child > td:nth-child(1)")
    private WebElement lastContactFirstName;

    @FindBy(css = ".v-grid-tablewrapper > table > tbody > tr:last-child > td:nth-child(2)")
    private WebElement lastContactLastName;

    @FindBy(css = ".v-grid-tablewrapper > table > thead > tr:first-child > th:first-child")
    private WebElement orderByFirstName;

    public AddressBookPage(WebDriver driver) {
        this.driver = driver;
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

    public AddressBookPage addUser(String fName, String lName) {
        return addUser(fName, lName, false);
    }

    public AddressBookPage addUser(String fName, String lName, boolean cancelOperation) {
        newButton.click();
        sleep();
        fNameField.sendKeys(fName);
        sleep();
        lNameField.sendKeys(lName);
        // TODO: fixed for now
        sleep();
        phoneField.sendKeys("+123-123-1231234");
        // TODO: fixed for now
        sleep();
        emailField.sendKeys("test@email.ts");
        // TODO: fixed for now
        sleep();
        birthdayField.sendKeys("1/1/15");
        sleep();
        if (cancelOperation) {
            cancelButton.click();
        } else {
            saveButton.click();
        }
        sleep();
        return this;
    }

    public AddressBookPage searchUsers(String searchTerm) {
        searchField.sendKeys(searchTerm);
        return this;
    }

    public AddressBookPage clearSearch() {
        searchField.clear();
        return this;
    }

    public AddressBookPage updateFirstContact(String firstName, String lastName) {
        firstContactFirstName.click();
        firstContactFirstName.clear();
        firstContactFirstName.sendKeys(firstName);
        firstContactLastName.clear();
        firstContactLastName.sendKeys(lastName);
        saveButton.click();
        return this;
    }

    public AddressBookPage updateLastContact(String firstName, String lastName) {
        lastContactFirstName.click();
        lastContactFirstName.clear();
        lastContactFirstName.sendKeys(firstName);
        lastContactLastName.clear();
        lastContactLastName.sendKeys(lastName);
        saveButton.click();
        return this;
    }

    public String getFirstNameOfFirstContactInList() {
        return firstContactFirstName.getText();
    }

    public String getLastNameOfFirstContactInList() {
        return firstContactLastName.getText();
    }

    public String getFirstNameOfLastContactInList() {
        return lastContactFirstName.getText();
    }

    public String getLastNameOfLastContactInList() {
        return lastContactLastName.getText();
    }

    public AddressBookPage orderByFirstName() {
        orderByFirstName.click();
        return this;
    }

    public int getContactCount() {
        return driver.findElements(By.cssSelector(".v-grid-tablewrapper > table > tbody > tr")).size();
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
