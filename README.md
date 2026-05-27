**Proiect Final - Framework Automatizare (UI & API)**

Acest proiect reprezinta un framework de testare automatizata conceput pentru a valida atat interfata grafica (UI - prin Selenium WebDriver), cat si serviciile de backend (API - prin RestAssured). Aplicatia foloseste Java, TestNG pentru managementul executiei si Maven pentru gestionarea dependentelor.

---

## Structura Principala a Proiectului

* `src/main/java/pages` -> Obiectele de pagina (Page Objects) folosite pentru izolarea elementelor web (Login, Dashboard, PIM).
* `src/test/java/tests/ui` -> Scenariile de testare pentru interfata grafica.
* `src/test/java/tests/api` -> Scenariile de testare pentru endpoint-urile API.
* `src/main/resources` -> Fisierele de configurare XML pentru TestNG (`ui-tests.xml`, `api-tests.xml`, `testing-master.xml`).
* `screenshots/` -> Directorul local unde se salveaza capturile de ecran pe disc.
* `allure-results/` -> Datele brute generate in timpul executiei, folosite pentru constructia raportului grafic.

```text
.
├── README.md
├── allure-results
├── extent-reports
├── pom.xml
├── screenshots
├── src
│   ├── config
│   │   └── config.properties
│   ├── main
│   │   ├── java
│   │   │   ├── pages
│   │   │   │   ├── BasePage.java
│   │   │   │   ├── DashboardPage.java
│   │   │   │   ├── LoginPage.java
│   │   │   │   ├── PimPage.java
│   │   │   │   └── RecruitmentPage.java
│   │   │   └── utils
│   │   │       ├── ConfigReader.java
│   │   │       ├── DriverFactory.java
│   │   │       ├── ExtentManager.java
│   │   │       ├── ScreenshotUtils.java
│   │   │       └── WaitUtils.java
│   │   └── resources
│   │       ├── api-tests.xml
│   │       ├── testing-master.xml
│   │       └── ui-tests.xml
│   └── test
│       └── java
│           ├── baseTest
│           │   └── BaseTest.java
│           ├── data
│           │   └── TestData.java
│           ├── listeners
│           │   ├── ExtentTestListener.java
│           │   └── TestListener.java
│           ├── testConfig
│           │   └── PetstoreConfig.java
│           └── tests
│               ├── api
│               │   └── APITests.java
│               └── ui
│                   ├── LoginTests.java
│                   ├── PimTests.java
│                   └── RecruitmentTests.java
```

---
## Structura detaliata a testelor

* `src/test/java/tests/ui` -> Scenarii de testare pentru Interfata Grafica (UI)
* `src/test/java/tests/api` -> Scenarii de testare pentru Backend (API)

## Teste UI
* **LoginTests:**
  * Verifica autentificarea cu succes a utilizatorului folosind credentials valide si redirectionarea pe Dashboard.
  * Testeaza gestionarea erorilor in mod controlat la introducerea unei parole gresite.
  * Verifica procesul de delogare securizata si redirectionarea inapoi la pagina de login.
* **PimTests:**
  * Testeaza adaugarea unui angajat nou in modulul PIM si incarcarea corecta a paginii de detalii personale.
  * Verifica functionalitatea de cautare si stergere definitiva a unui angajat din tabel pe baza ID-ului sau.
* **RecruitmentTests:**
  * Verifica fluxul de adaugare a unui candidat nou in modulul de Recrutare si validarea trecerii in etapa curenta a aplicatiei (*Application Stage*).

## Teste API
* **APITests (Flux Complet CRUD - Swagger Petstore):**
  * **addNewPetTest (POST):** Scenariu Pozitiv. Trimite detaliile unui nou animal de companie in sistem si valideaza salvarea corecta (ID, nume, status disponibil) cu status 200 OK.
  * **getPetByIdTest (GET):** Scenariu Pozitiv (depinde de crearea cu succes). Extrage datele animalului folosind ID-ul ca parametru de cale si verifica integritatea informatiilor returnate.
  * **updatePetTest (PUT):** Scenariu Pozitiv. Modifica numele resursei existente in sistem si verifica daca serverul salveaza cu succes noile date.
  * **deletePetTest (DELETE):** Scenariu Pozitiv. Sterge definitiv animalul de companie creat din baza de date si confirma eliminarea resursei.

---

## Instructiuni de Rulare

Toate comenzile se executa din terminal, pozitionat in directorul radacina al proiectului.

**Rularea intregii suite de teste (UI + API):**
```bash
mvn clean test
```

**Rularea exclusiva a suitei pentru API:**
```bash
mvn test -Dsurefire.suiteXmlFiles=src/main/resources/api-tests.xml
```

**Rularea exclusiva a suitei pentru UI:**
```bash
mvn test -Dsurefire.suiteXmlFiles=src/main/resources/ui-tests.xml
```

**Vizualizarea Raportului Allure**
```bash
allure serve allure-results
```
