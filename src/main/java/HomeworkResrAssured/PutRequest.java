package HomeworkResrAssured;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class PutRequest {
    public static void main(String[] args) {
        given()
                .baseUri("https://api.restful-api.dev")
                .contentType("application/json")
                .body("{\n" +
                        "   \"name\": \"Apple12 MacBook MiniPro5654 216\",\n" +
                        "   \"data\": {\n" +
                        "      \"year\": 1990,\n" +
                        "      \"price\": 2234.12,\n" +
                        "      \"CPU model\": \"Intel Core i11\",\n" +
                        "      \"Hard disk size\": \"128 GB\",\n" +
                        "      \"color\": \"green\"\n" +
                        "   }\n" +
                        "}"  )
                .when()
                .put("/objects/ff808181932badb60196bec4a8d470c9")
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("update-schema.json"));

    }
}
