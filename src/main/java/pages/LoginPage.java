package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// clase(pagini) in care declaram elementele si interactiunea vizuala

public class LoginPage extends BasePage {

    // locatori
    @FindBy(name = "username")
    private WebElement usernameField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;

    @FindBy(xpath = "//p[contains(@class, 'oxd-alert-content-text')]")
    private WebElement errorMessage;

    @FindBy(xpath = "//h5[text()='Login']")
    private WebElement loginHeader;

    // constructor
    public LoginPage(WebDriver driver) {
       super(driver);
        PageFactory.initElements(driver, this);
    }

    // actiuni
    public void enterUsername(String username) {
        waitUtils.waitForElementVisible(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
       passwordField.sendKeys(password);
    }

    public String getErrorMessage() {
        return waitUtils.waitForElementVisible(errorMessage).getText();
    }

    public void clickLogin() {
        loginButton.click();
    }

    public WebElement getLoginHeader() {
        return waitUtils.waitForElementVisible(loginHeader);
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }
}
