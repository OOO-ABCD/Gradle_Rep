package HomeworkResrAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.notNullValue;

public class DeleteRequest {

    public static void main(String[] args) {

        given()
                .baseUri("https://api.restful-api.dev")
                .contentType("application/json")
                .when()
                .delete("/objects/ff808181932badb60196bf3c63a07140")
                .then()
                .log().all()
                .statusCode(200)
                .body (notNullValue())
                .extract().asString();

    }
}
