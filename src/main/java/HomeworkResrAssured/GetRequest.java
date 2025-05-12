package HomeworkResrAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class GetRequest {

    public static void main(String[] args) {

        given()
                .baseUri("https://api.restful-api.dev")
                .basePath("/objects/7")
                .header("content-type", "application/json")
                .when()
                .get()
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body ("name", containsString("Apple"))
                .body("data.year", equalTo(2019))
                .body("data.\"CPU model\"", matchesPattern(".*i9$"));

    }

}
