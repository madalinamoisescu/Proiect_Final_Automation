package tests.ui;

import baseTest.BaseTest;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.RecruitmentPage;
import utils.ScreenshotUtils;

import static org.testng.AssertJUnit.assertTrue;

public class RecruitmentTests extends BaseTest {

    @Test
    public void addCandidateTest() {
        // Scop: Verificarea functionalitatii de adaugare a unui candidat nou in modulul de Recrutare (Recruitment).
        // Scenariu: Autentificare -> Navigare la modulul Recruitment -> Click pe butonul Add -> Completare formular candidat -> Salvare -> Verificare afisare etapa curenta a aplicatiei.

        Reporter.log("[START] Incepe rularea testului: addCandidateTest.");
        LoginPage loginPage = new LoginPage(driver);

        Reporter.log("[STEP] Autentificare in aplicatie cu contul de Admin.");
        loginPage.login("Admin", "admin123");

        DashboardPage dashboardPage = new DashboardPage(driver);
        Reporter.log("[STEP] Navigare catre meniul principal Recruitment.");
        dashboardPage.navigateToRecruitment();

        RecruitmentPage recruitmentPage = new RecruitmentPage(driver);
        Reporter.log("[STEP] Accesare formular de adaugare prin apasarea butonului Add.");
        recruitmentPage.clickAddButton();

        Reporter.log("[STEP] Completare si trimitere formular cu datele noului candidat.");
        recruitmentPage.fillInRecruitmentForm();

        Reporter.log("[STEP] Verificare reusita: Se controleaza daca header-ul etapei aplicatiei (Application Stage) este vizibil.");
        assertTrue("Header-ul Application Stage ar trebui sa fie vizibil.", recruitmentPage.getApplicationStageHeader().isDisplayed());

        Reporter.log("[INFO] Capturare screenshot pentru confirmarea adaugarii candidatului.");
        ScreenshotUtils.takeScreenshot(driver, "validareAdaugareCandidat");
        Reporter.log("[SUCCESS] Testul addCandidateTest s-a finalizat cu succes.");
    }
}