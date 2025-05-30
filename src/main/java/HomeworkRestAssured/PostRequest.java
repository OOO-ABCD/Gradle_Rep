package HomeworkRestAssured;

import com.google.gson.Gson;

import static io.restassured.RestAssured.given;

public class PostRequest {

    public static void main(String[] args) {

        RegisterRequest.Data data = new RegisterRequest.Data (1996, 1955.76, "Intel Core i9", "Intel Core i11");
        RegisterRequest request = new RegisterRequest ("Sony VAIO", data);

        Gson gson = new Gson();

        String jsonBody = gson.toJson(request);

        RegisterResponse response = given()
                .baseUri("https://api.restful-api.dev")
                .basePath("/objects")
                .contentType("application/json")
                .body (jsonBody)
                .when()
                .post()
                .then()
                .log().all()
                .extract().as(RegisterResponse.class);

        assert response.id != null && !response.id.isEmpty();

    }

}

