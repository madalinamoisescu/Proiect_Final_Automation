package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;

public class RecruitmentPage extends BasePage {

    // locatori
    @FindBy(xpath = "//button[@class='oxd-button oxd-button--medium oxd-button--secondary']")
    private WebElement addButton;

    @FindBy(name = "firstName")
    private WebElement firstName;

    @FindBy(name = "lastName")
    private WebElement lastName;

    @FindBy(xpath = "//div[@class='oxd-select-text-input']")
    private WebElement vacancyDropdown;

    @FindBy(xpath = "//label[text()='Email']/following::input[1]")
    private WebElement email;

    @FindBy(xpath = "//textarea[@class='oxd-textarea oxd-textarea--active oxd-textarea--resize-vertical']")
    private WebElement notes;

    @FindBy(xpath = "//button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']")
    private WebElement saveButton;

    @FindBy(xpath = "//h6[text()='Application Stage']")
    private WebElement applicationStageHeader;

    // constructor
    public RecruitmentPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // actiuni
    public void clickAddButton() {
        waitUtils.waitForElementVisible(addButton).click();
    }

    // metoda pt dropdown; selecteaza tasta sageata jos si apoi enter pt a selecta pozitia
    public void selectDropdownVacancyByPosition(int option) {
        waitUtils.waitForElementVisible(vacancyDropdown).click();
        for (int i = 0; i < option; i++) {
            vacancyDropdown.sendKeys(Keys.ARROW_DOWN);
        }
        vacancyDropdown.sendKeys(Keys.ENTER);

    }
    // metoda pt upload document
    public void uploadResume() {
        By fileInputLocator = By.xpath("//input[@type='file']");
        WebElement fileInput = driver.findElement(fileInputLocator);

        File file = new File("/Users/dippyfresh/dummy.docx.pdf");
        String absolutePath = file.getAbsolutePath();

        fileInput.sendKeys(absolutePath);
    }

    public void fillInRecruitmentForm() {
        waitUtils.waitForElementVisible(firstName).sendKeys("Ana");
        waitUtils.waitForElementVisible(lastName).sendKeys("Lia");
        selectDropdownVacancyByPosition(3);
        waitUtils.waitForElementVisible(email).sendKeys("test@test.com");
        uploadResume();
        waitUtils.waitForElementVisible(notes).sendKeys("Lorem ipsum");
        saveButton.click();
    }

    public WebElement getApplicationStageHeader() {
        return waitUtils.waitForElementVisible(applicationStageHeader);
    }

}
