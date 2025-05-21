import HomeworkRestAssured.RegisterRequest;
import HomeworkRestAssured.RegisterResponse;
import com.google.gson.Gson;
import org.junit.jupiter.api.*;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import io.restassured.response.Response;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JunitRestAssuredTest {
    private static String createdUserId;
    private static final String url = "https://api.restful-api.dev";

    @Order(1)
    @Test
        public void getRequest() {
        Response response = given()
                    .baseUri(url)
                    .basePath("/objects/7")
                    .header("content-type", "application/json")
                    .when()
                    .get()
                    .then()
                    .statusCode(200)
                    .contentType("application/json")
                    .body ("name", containsString("Apple"))
                    .body("data.year", equalTo(2019))
                    .body("data.\"CPU model\"", matchesPattern(".*i9$"))
                    .extract()
                    .response();

        String name = response.jsonPath().getString("name");
        int year = response.jsonPath().getInt("data.year");
        assertEquals(200, response.getStatusCode(), "Неверный статус код");
        assertThat(name).contains("Apple");
        assertThat(year).isEqualTo(2019);
        }

        @Order(2)
        @Test
        public void postRequest() {

            RegisterRequest.Data data = new RegisterRequest.Data(1996, 1955.76, "Intel Core i9", "Intel Core i11");
            RegisterRequest request = new RegisterRequest("Sony VAIO", data);
            Gson gson = new Gson();
            String jsonBody = gson.toJson(request);

            RegisterResponse response = given()
                    .baseUri("https://api.restful-api.dev")
                    .basePath("/objects")
                    .contentType("application/json")
                    .body(jsonBody)
                    .when()
                    .post()
                    .then()
                    .log().all()
                    .extract().as(RegisterResponse.class);

            System.out.println("ID" + response.id);
            assertTrue(response.id != null && !response.id.isEmpty());
            createdUserId = response.id;
        }

        @Order(3)
        @Test
        public void putRequest() {
        Response response = given()
                .baseUri("https://api.restful-api.dev")
                .contentType("application/json")
                .body("{\n" +
                        "   \"name\": \"Apple101 MacBook MiniPro5654 256\",\n" +
                        "   \"data\": {\n" +
                        "      \"year\": 1991,\n" +
                        "      \"price\": 2234.12,\n" +
                        "      \"CPU model\": \"Intel Core i11\",\n" +
                        "      \"Hard disk size\": \"128 GB\",\n" +
                        "      \"color\": \"green\"\n" +
                        "   }\n" +
                        "}"  )
                .when()
                .put("/objects/" + createdUserId)
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("update-schema.json"))
                .extract()
                .response();

            String id = response.jsonPath().getString("id");
            assertTrue(id != null);
            assertNotNull(id);
            String color = response.jsonPath().getString("data.color");
            assertEquals("green", color);
        }

        @Order(4)
        @Test
        public void deleteRequest() {
        Response response = given()
                    .baseUri("https://api.restful-api.dev")
                    .contentType("application/json")
                    .when()
                    .delete("/objects/" + createdUserId)
                    .then()
                    .log().all()
                    .statusCode(200)
                    .body (notNullValue())
                    .extract()
                    .response();

            assertNotNull(createdUserId);
            String responseBody = response.asString();
            assertTrue(responseBody.contains("deleted"));
        }
}
