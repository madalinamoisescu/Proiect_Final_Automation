package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage extends BasePage {

    // locatori
    @FindBy(xpath = "//span[text()='PIM']")
    private WebElement pimMenu;

    @FindBy(className = "oxd-userdropdown-name")
    private WebElement userDropdown;

    @FindBy(xpath = "//span[text()='Recruitment']")
    private WebElement recruitmentMenu;

    @FindBy(xpath = "//span[text()='Dashboard']")
    private WebElement dashboardMenu;

    @FindBy(xpath = "//a[text()='Logout']")
    private WebElement logoutLink;

    // constructor
    public DashboardPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // actiuni
    public void navigateToPim() {
        waitUtils.waitForElementVisible(pimMenu).click();
    }

    public void navigateToRecruitment() {
        waitUtils.waitForElementVisible(recruitmentMenu).click();
    }

    public WebElement getDashboardHeader() {
        return waitUtils.waitForElementVisible(dashboardMenu);
    }

    public void logout() {
        waitUtils.waitForElementVisible(userDropdown).click();
        waitUtils.waitForElementVisible(logoutLink).click();
    }
}
