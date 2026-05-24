package tests.ui;

import baseTest.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.PimPage;

import static org.testng.AssertJUnit.assertTrue;

public class PimTests extends BaseTest {

    @Test
    public void createEmployeeTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");

        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.navigateToPim();

        PimPage pimPage = new PimPage(driver);
        pimPage.addNewEmployee("Ana", "Lia", "08981");

        assertTrue("Employee ID cell should be visible.", pimPage.getPersonalDetailsHeader().isDisplayed());
    }

    @Test
    public void searchAndDeleteEmployeeTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");

        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.navigateToPim();

        PimPage pimPage = new PimPage(driver);
        pimPage.searchEmployeeById("08981");

        pimPage.deleteFirstEmployeeFromList();

        pimPage.searchEmployeeById("08981");

        boolean isDeleted = pimPage.isEmployeeIdInvisible("08981");
        assertTrue("Employee ID cell should be visible.", isDeleted);
    }

}
