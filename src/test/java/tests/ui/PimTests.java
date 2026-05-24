package tests.ui;

import baseTest.BaseTest;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.PimPage;
import utils.ScreenshotUtils;

import static org.testng.AssertJUnit.assertTrue;

public class PimTests extends BaseTest {

    @Test
    public void createEmployeeTest() {
        // Scop: Verificarea functionalitatii de adaugare a unui nou angajat in modulul PIM.
        // Scenariu: Autentificare -> Navigare la modulul PIM -> Introducere date angajat nou (Nume, Prenume, ID) -> Salvare -> Verificare incarcare pagina de detalii personale.

        String firstName = "Ana";
        String lastName = "Lia";
        String employeeId = "08981";

        Reporter.log("[START] Incepe rularea testului: createEmployeeTest.");
        LoginPage loginPage = new LoginPage(driver);

        Reporter.log("[STEP] Autentificare in aplicatie cu contul de Admin.");
        loginPage.login("Admin", "admin123");

        DashboardPage dashboardPage = new DashboardPage(driver);
        Reporter.log("[STEP] Navigare catre meniul principal PIM.");
        dashboardPage.navigateToPim();

        PimPage pimPage = new PimPage(driver);
        Reporter.log("[STEP] Adaugare angajat nou -> Nume: " + firstName + " " + lastName + " | ID: " + employeeId);
        pimPage.addNewEmployee(firstName, lastName, employeeId);

        Reporter.log("[STEP] Verificare reusita: Se controleaza daca header-ul de detalii personale este vizibil.");
        assertTrue("Header-ul detaliilor personale ar trebui sa fie vizibil.", pimPage.getPersonalDetailsHeader().isDisplayed());

        Reporter.log("[INFO] Capturare screenshot pentru confirmarea crearii angajatului.");
        ScreenshotUtils.takeScreenshot(driver, "validareCreareAngajat");
        Reporter.log("[SUCCESS] Testul createEmployeeTest s-a finalizat cu succes.");
    }

    @Test
    public void searchAndDeleteEmployeeTest() {
        // Scop: Verificarea functionalitatii de cautare si stergere a unui angajat existent dupa ID.
        // Scenariu: Autentificare -> Navigare la PIM -> Cautare angajat dupa ID -> Stergere primul rezultat -> Cautare din nou dupa acelasi ID -> Verificare disparitie element din tabel.

        String employeeId = "08981";

        Reporter.log("[START] Incepe rularea testului: searchAndDeleteEmployeeTest.");
        LoginPage loginPage = new LoginPage(driver);

        Reporter.log("[STEP] Autentificare in aplicatie cu contul de Admin.");
        loginPage.login("Admin", "admin123");

        DashboardPage dashboardPage = new DashboardPage(driver);
        Reporter.log("[STEP] Navigare catre meniul principal PIM.");
        dashboardPage.navigateToPim();

        PimPage pimPage = new PimPage(driver);
        Reporter.log("[STEP] Cautare initiala a angajatului dupa ID: " + employeeId);
        pimPage.searchEmployeeById(employeeId);

        Reporter.log("[STEP] Executare actiune de stergere pentru primul angajat gasit in lista.");
        pimPage.deleteFirstEmployeeFromList();

        Reporter.log("[STEP] Re-cautare angajat dupa ID pentru a verifica eliminarea acestuia.");
        pimPage.searchEmployeeById(employeeId);

        Reporter.log("[STEP] Verificare reusita: Se controleaza daca ID-ul angajatului a devenit indisponibil.");
        boolean isDeleted = pimPage.isEmployeeIdInvisible(employeeId);
        assertTrue("ID-ul angajatului ar trebui sa fie indisponibil dupa stergere.", isDeleted);

        Reporter.log("[INFO] Capturare screenshot pentru a confirma ca tabelul este gol.");
        ScreenshotUtils.takeScreenshot(driver, "validareStergereAngajat");
        Reporter.log("[SUCCESS] Testul searchAndDeleteEmployeeTest s-a finalizat cu succes.");
    }
}