package tests.api;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

public class APITests {

    // test 1 adaugare pet nou
    @Test
    public void addNewPetTest() {
        int petId = 81;

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

        given()
                .contentType("application/json")
                .body(customBody)
                .when()
                .post("https://petstore.swagger.io/v2/pet/")
                .then()
                .statusCode(200)
                .body("id", equalTo(petId))
                .body("name", equalTo("Rena"))
                .body("status", equalTo("available"));
    }

    // test 2 verifica pet dupa id
    @Test
    public void getPetByIdTest() {
        given()
                .pathParams("id", 81)
                .when()
                .get("https://petstore.swagger.io/v2/pet/{id}")
                .then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("id", equalTo(81))
                .body("name", equalTo("Rena"));
    }

    // test 3 update pet
    @Test
    public void updatePetTest() {
        int petId = 81;

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

        given()
                .contentType("application/json")
                .body(customBody)
                .when()
                .put("https://petstore.swagger.io/v2/pet")
                .then()
                .statusCode(200);
    }

    // test 4 stergere pet
    @Test
    public void deletePetTest() {
        given()
                .pathParams("id", 81)
                .when()
                .delete("https://petstore.swagger.io/v2/pet/{id}")
                .then()
                .statusCode(200);
    }
}
