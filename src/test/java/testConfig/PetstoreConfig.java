package config;

public class PetstoreConfig {
    // Domeniul de baza pentru serviciul Petstore
    public static final String PETSTORE_API_DOMAIN = "https://petstore.swagger.io/v2";

    // Endpoint-uri specifice pentru entitatea Pet
    public static final String PET_ENDPOINT = "/pet";
    public static final String PET_BY_ID_ENDPOINT = PET_ENDPOINT + "/{id}";
}