package tests.ui;

import baseTest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.PimPage;

public class LoginTests extends BaseTest {

    @Test
    public void successfulLoginTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");

        Assert.assertTrue(driver.getCurrentUrl().contains("/dashboard"));
    }

    @Test
    public void invalidPasswordTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "parolagresita");

        Assert.assertEquals(loginPage.getErrorMessage(), "Invalid credentials");
        System.out.println();
    }

    @Test
    public void logoutTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");

        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.logout();

        Assert.assertTrue(driver.getCurrentUrl().contains("/auth/login"));
    }

}
