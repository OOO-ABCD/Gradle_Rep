package HomeworkResrAssured;

import lombok.ToString;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import io.restassured.response.Response;

public class PostRequest {

    public static void main(String[] args) {

       Response response = given()
               .baseUri("https://api.restful-api.dev")
               .basePath("/objects")
               .contentType("application/json")
               .body("{\"name\": \"Apple MacBook No Pro 777\", \"data\": { \"year\": 2022," +
                       " \"price\": 2615.23, \"CPU model\": \"Intel Core i7\"," +
                       " \"Hard disk size\": \"256 GB\"}}")
               .when()
               .post()
               .then()
               .statusCode(200)
               .header("content-type", "application/json")
               .body ("name", containsString("Apple"))
               .extract()
               .response();

        String id = response.jsonPath().getString("id");
        System.out.println("New product ID: " + id);
        //не знаю почему, но id создается как стринг (смотрел респонс через hoppscotch, в документации int стоит)
        //плюс статус респонса по документации 200, поэтому такой и оставил в тесте


    }

}
