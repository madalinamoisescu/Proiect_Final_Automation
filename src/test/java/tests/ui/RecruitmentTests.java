package tests.ui;

import baseTest.BaseTest;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.RecruitmentPage;

import static org.testng.AssertJUnit.assertTrue;

public class RecruitmentTests extends BaseTest {

    @Test
    public void addCandidateTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");

        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.navigateToRecruitment();

        RecruitmentPage recruitmentPage = new RecruitmentPage(driver);
        recruitmentPage.clickAddButton();
        recruitmentPage.fillInRecruitmentForm();

        assertTrue("Application Stage", recruitmentPage.getApplicationStageHeader().isDisplayed());
    }
}
