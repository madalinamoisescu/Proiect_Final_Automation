package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PimPage extends BasePage {

    // locatori
    @FindBy(xpath = "//a[text()='Add Employee']")
    private WebElement addEmployeeButton;

    @FindBy(name = "firstName")
    private WebElement firstNameField;

    @FindBy(name = "lastName")
    private WebElement lastNameField;

    @FindBy(xpath = "//label[text()='Employee Id']/following::input[1]")
    private WebElement employeeIdField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveButton;

    @FindBy(xpath = "//h6[text()='Personal Details']")
    private WebElement personalDetailsHeader;

    @FindBy(xpath = "//a[text()='Employee List']")
    private WebElement employeeListButton;

    @FindBy(xpath = "//label[text()='Employee Id']/parent::div/following-sibling::div/input")
    private WebElement searchByIdField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement searchButton;

    @FindBy(className = "bi-trash")
    private WebElement deleteIcon;

    @FindBy(className = "oxd-button--label-danger")
    private WebElement confirmDeleteButton;

    // constructor
    public PimPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // actiuni/metode
    public void addNewEmployee(String firstName, String lastName, String employeeId) {
        waitUtils.waitForElementVisible(addEmployeeButton).click();
        waitUtils.waitForElementVisible(firstNameField).sendKeys(firstName);
        waitUtils.waitForElementVisible(lastNameField).sendKeys(lastName);

        while (!employeeIdField.getAttribute("value").equals("")) {
            employeeIdField.sendKeys(Keys.BACK_SPACE);
        }
        employeeIdField.sendKeys(employeeId);
        saveButton.click();
    }

    public void searchEmployeeById(String employeeId) {
        waitUtils.waitForElementVisible(employeeListButton).click();

        waitUtils.waitForElementVisible(searchByIdField).sendKeys(employeeId);
        searchButton.click();
    }

    public WebElement getPersonalDetailsHeader() {
        return waitUtils.waitForElementVisible(personalDetailsHeader);
    }

    public void deleteFirstEmployeeFromList() {
        waitUtils.waitForElementVisible(deleteIcon).click();
        waitUtils.waitForElementVisible(confirmDeleteButton).click();
    }

    public boolean isEmployeeIdInvisible(String employeeId) {
        By idLocator = By.xpath("//div[@role='cell']//div[text()='" + employeeId + "']");
        return driver.findElements(idLocator).isEmpty();
    }
}
