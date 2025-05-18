package Lesson_26.wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;

public class WireMock {

    public static void main(String[] args) {

        WireMockServer wireMockServer = new WireMockServer(8080);
        wireMockServer.start();

        String url = "http://localhost:8080";
        String jsonBody = "{ \"method\": \"POST\", \"status\": 201, \"id\": 2," +
                "\"phone\": \"Samsung\", \"Model\": \"Galaxy\", \"Condition\": \"Used\" }";
        
        stubFor(get(urlPathEqualTo("/api/gadgets/phones/1"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{ \"id\" : 1, \"phone\" : \"Iphone\", \"model\" : \"15 Pro\"," + "\"condition\" : \"New\"}")));

        given()
                .baseUri(url)
                .queryParam("model", "15 Pro")
                .header("Authorization", "GetToken")
                .when()
                .get("/api/gadgets/phones/1");

        verify(getRequestedFor(urlPathEqualTo("/api/gadgets/phones/1"))
                .withQueryParam("model", equalTo("15 Pro"))
                .withHeader("Authorization", equalTo("GetToken")));

        stubFor(post(urlPathEqualTo("/api/gadgets/phones"))
                .withHeader("Authorization", containing("PostToken"))
                .withRequestBody(equalToJson(jsonBody))
                .willReturn(aResponse()
                        .withStatus(201)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"method\":\"POST\",\"status\":201, \"id\" : 2," +
                                "\"phone\" : \"Samsung\", \"Model\" : \"Galaxy\", \"Condition\" : \"Used\"}")
                        .withFixedDelay(333)));

        given()
                .baseUri(url)
                .header("Authorization", "PostToken")
                .body(jsonBody)
                .when()
                .post("/api/gadgets/phones")
                .then()
                .statusCode(201);

        verify(1, postRequestedFor(urlPathEqualTo("/api/gadgets/phones")));

        stubFor(put(urlPathEqualTo("/api/gadgets/phones"))
                .withHeader("Content-Type", containing("application/json"))
                .withHeader("Authorization", equalTo("PutToken"))
                .withRequestBody(containing("\"Updated successfully\":true"))

                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"method\":\"PUT\",\"status\":200}")
                        .withFixedDelay(100)));

        given()
                .baseUri(url)
                .body("{\"Updated successfully\":true}")
                .when()
                .put("/api/gadgets/phones");

        verify(putRequestedFor(urlPathEqualTo("/api/gadgets/phones")));

        stubFor(delete(urlPathEqualTo("/api/gadgets/phones/Samsung"))
                .withHeader("Authorization", containing("DeleteToken"))

                .willReturn(aResponse()
                        .withStatus(204)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"method\":\"DELETE\",\"status\":200}")
                        .withUniformRandomDelay(250, 400)));

        given()
                .baseUri(url)
                .when()
                .header("Authorization", "DeleteToken")
                .delete("/api/gadgets/phones/Samsung");

        verify(deleteRequestedFor(urlPathEqualTo("/api/gadgets/phones/Samsung"))
                .withHeader("Authorization", equalTo("DeleteToken")));

        wireMockServer.stop();

    }

}
