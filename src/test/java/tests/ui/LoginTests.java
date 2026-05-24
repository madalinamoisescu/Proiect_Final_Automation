package tests.ui;

import baseTest.BaseTest;
import data.TestData;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import utils.ScreenshotUtils;
import utils.WaitUtils;

public class LoginTests extends BaseTest {

    @Test
    public void successfulLoginTest() {
        // Scop: Verificarea faptului ca un utilizator se poate autentifica cu succes folosind credentiale valide.
        // Scenariu: Navigare la pagina de login -> Introducere credentiale valide -> Click pe butonul de login -> Verificare redirectionare catre dashboard.
        Reporter.log("[START] Incepe rularea testului: successfulLoginTest.");
        LoginPage loginPage = new LoginPage(driver);

        Reporter.log("[STEP] Trimitere credentiale valide -> Username: " + TestData.USERNAME + " | Password: " + TestData.PASSWORD);
        loginPage.login(TestData.USERNAME, TestData.PASSWORD);

        DashboardPage dashboardPage = new DashboardPage(driver);
        Reporter.log("[STEP] Verificare: daca dashboard este afisat in pagina.");
        Assert.assertTrue(dashboardPage.getDashboardHeader().isDisplayed(), "Dashboard is displayed.");

        Reporter.log("[INFO] Capturare screenshot pentru validarea succesului login-ului.");
        ScreenshotUtils.takeScreenshot(driver, "validareLoginPositive");
        Reporter.log("[SUCCESS] Testul successfulLoginTest s-a finalizat cu succes.");
    }

    @Test
    public void invalidPasswordTest() {
        // Scop: Verificarea faptului ca procesul de login esueaza in mod controlat atunci cand se introduce o parola gresita.
        // Scenariu: Navigare la pagina de login -> Introducere username valid + parola gresita -> Click pe butonul de login -> Verificare afisare mesaj de eroare corespunzator.
        String password = "parolagresita";
        String expectedErrorMessage = "Invalid credentials";

        Reporter.log("[START] Incepe rularea testului: invalidPasswordTest.");
        LoginPage loginPage = new LoginPage(driver);

        Reporter.log("[STEP] Trimitere credentiale invalide -> Username: " + TestData.USERNAME + " | Password: " + password);
        loginPage.login(TestData.USERNAME, password);

        Reporter.log("[STEP] Verificare mesaj de eroare. Se asteapta textul: '" + expectedErrorMessage + "'");
        String actualErrorMessage = loginPage.getErrorMessage();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);

        Reporter.log("[INFO] Capturare screenshot pentru salvarea starii de eroare afisate.");
        ScreenshotUtils.takeScreenshot(driver, "validareLoginNegative_ParolaGresita");
        Reporter.log("[SUCCESS] Testul invalidPasswordTest s-a finalizat cu succes.");
    }

    @Test
    public void logoutTest() {
        // Scop: Verificarea faptului ca un utilizator autentificat se poate deconecta in siguranta din aplicatie.
        // Scenariu: Efectuare login valid -> Navigare automata pe dashboard -> Declanşare procedura de delogare -> Verificare redirectionare inapoi la pagina de login.

        String username = "Admin";
        String password = "admin123";

        Reporter.log("[START] Incepe rularea testului: logoutTest.");
        LoginPage loginPage = new LoginPage(driver);

        Reporter.log("[STEP] Conectare initiala in aplicatie pentru a putea efectua delogarea ulterior.");
        loginPage.login(username, password);

        Reporter.log("[STEP] Accesare DashboardPage si initializare procedura de delogare.");
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.logout();

        Reporter.log("[STEP] Verificare reusita delogare: Se verificat daca delogarea a fost efectuata cu success prin verificarea headerului.");
        Assert.assertTrue(loginPage.getLoginHeader().isDisplayed(), "Login header is displayed.");

        Reporter.log("[INFO] Capturare screenshot dupa delogare reusita.");
        ScreenshotUtils.takeScreenshot(driver, "validareLogoutAction");
        Reporter.log("[SUCCESS] Testul logoutTest s-a finalizat cu succes.");
    }

}
