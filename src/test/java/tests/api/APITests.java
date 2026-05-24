package tests.api;

import config.PetstoreConfig;
import org.testng.Reporter;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

public class APITests {

    private final int petId = 81;

    @Test(priority = 1)
    public void addNewPetTest() {
        // Scop: Verificarea functionalitatii de adaugare a unui nou animal de companie (Pet) in sistem.
        // Scenariu: Trimitere cerere POST cu un body JSON valid continand detaliile noului Pet -> Verificare status 200 si validare date salvate (ID, nume, status).

        String customBody = "{\n" +
                "  \"id\": " + petId + ",\n" +
                "  \"category\": {\n" +
                "    \"id\": " + petId + ",\n" +
                "    \"name\": \"Caine\"\n" +
                "  },\n" +
                "  \"name\": \"Rena\",\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": " + petId + ",\n" +
                "      \"name\": \"Caine\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";

        Reporter.log("[START] Incepe rularea testului de API: addNewPetTest.");
        Reporter.log("[STEP] Trimitere cerere POST pentru adaugare Pet cu ID: " + petId);
        Reporter.log("[STEP] Trimitere cerere POST pentru adaugare Pet cu BODY: " + customBody);

        given()
                .contentType("application/json")
                .body(customBody)
                .when()
                .post(PetstoreConfig.PETSTORE_API_DOMAIN + PetstoreConfig.PET_ENDPOINT)
                .then()
                .statusCode(200)
                .body("id", equalTo(petId))
                .body("name", equalTo("Rena"))
                .body("status", equalTo("available"));

        Reporter.log("[SUCCESS] Testul addNewPetTest s-a finalizat cu succes. Pet-ul a fost adaugat.");
    }

    @Test(priority = 2, dependsOnMethods = "addNewPetTest")
    public void getPetByIdTest() {
        // Scop: Verificarea extragerii corecte a datelor unui Pet existent folosind ID-ul ca parametru de cale.
        // Scenariu: Trimitere cerere GET catre endpoint-ul specific cu ID-ul setat in path -> Verificare status 200 si validare corectitudine date returnate.

        Reporter.log("[START] Incepe rularea testului de API: getPetByIdTest.");
        Reporter.log("[STEP] Trimitere cerere GET pentru preluare Pet cu ID: " + petId);

        given()
                .pathParams("id", petId)
                .when()
                .get(PetstoreConfig.PETSTORE_API_DOMAIN + PetstoreConfig.PET_BY_ID_ENDPOINT)
                .then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("id", equalTo(petId))
                .body("name", equalTo("Rena"));

        Reporter.log("[SUCCESS] Testul getPetByIdTest s-a finalizat cu succes. Datele Pet-ului coincid.");
    }

    @Test(priority = 3, dependsOnMethods = "getPetByIdTest")
    public void updatePetTest() {
        // Scop: Verificarea functionalitatii de actualizare a informatiilor unui Pet existent.
        // Scenariu: Trimitere cerere PUT cu datele modificate (schimbare nume din Rena in Mia) -> Verificare status 200 in raspunsul serverului.

        String customBody = "{\n" +
                "  \"id\": " + petId + ",\n" +
                "  \"category\": {\n" +
                "    \"id\": " + petId + ",\n" +
                "    \"name\": \"Caine\"\n" +
                "  },\n" +
                "  \"name\": \"Mia\",\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": " + petId + ",\n" +
                "      \"name\": \"Caine\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";

        Reporter.log("[START] Incepe rularea testului de API: updatePetTest.");
        Reporter.log("[STEP] Trimitere cerere PUT pentru actualizare nume in 'Mia' pentru Pet-ul cu ID: " + petId);
        Reporter.log("[STEP] Trimitere cerere PUT pentru actualizare nume in 'Mia' pentru Pet-ul cu BODY: " + customBody);

        given()
                .contentType("application/json")
                .body(customBody)
                .when()
                .put(PetstoreConfig.PETSTORE_API_DOMAIN + PetstoreConfig.PET_ENDPOINT)
                .then()
                .statusCode(200);

        Reporter.log("[SUCCESS] Testul updatePetTest s-a finalizat cu succes. Modificarile au fost salvate.");
    }

    @Test(priority = 4, dependsOnMethods = "updatePetTest")
    public void deletePetTest() {
        // Scop: Verificarea stergerii definitive a unui Pet din baza de date a aplicatiei.
        // Scenariu: Trimitere cerere DELETE folosind ID-ul obiectului creat -> Verificare raspuns cu status code 200.

        Reporter.log("[START] Incepe rularea testului de API: deletePetTest.");
        Reporter.log("[STEP] Trimitere cerere DELETE pentru eliminare Pet cu ID: " + petId);

        given()
                .pathParams("id", petId)
                .when()
                .delete(PetstoreConfig.PETSTORE_API_DOMAIN + PetstoreConfig.PET_BY_ID_ENDPOINT)
                .then()
                .statusCode(200);

        Reporter.log("[SUCCESS] Testul deletePetTest s-a finalizat cu succes. Resursa a fost eliminata.");
    }
}