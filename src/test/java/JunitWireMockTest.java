import Configuration.WireMockConfig;
import com.github.tomakehurst.wiremock.verification.LoggedRequest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.*;
import static io.restassured.RestAssured.given;
import java.util.List;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import io.restassured.response.Response;

public class JunitWireMockTest {
    private static final String url = "http://localhost:8080";

    @BeforeAll
    public static void startServer() {
        WireMockConfig.startServer();
    }

    @AfterAll
    public static void stopServer() {
        WireMockConfig.stopServer();
    }

    @BeforeAll
    public static void stubs() {
        String jsonBody = "{ \"method\": \"POST\", \"status\": 201, \"id\": 2," +
                "\"phone\": \"Samsung\", \"Model\": \"Galaxy\", \"Condition\": \"Used\" }";

        stubFor(get(urlPathEqualTo("/api/gadgets/phones/1"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{ \"id\" : 1, \"phone\" : \"Iphone\"," +
                                "\"model\" : \"15 Pro\", \"condition\" : \"New\"}")));

        stubFor(post(urlPathEqualTo("/api/gadgets/phones"))
                .withHeader("Authorization", containing("PostToken"))
                .withRequestBody(equalToJson(jsonBody))
                .willReturn(aResponse()
                        .withStatus(201)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"method\":\"POST\",\"status\":201, \"id\" : 2," +
                                "\"phone\" : \"Samsung\", \"Model\" : \"Galaxy\"," +
                                "\"Condition\" : \"Used\"}")
                        .withFixedDelay(333)));

        stubFor(put(urlPathEqualTo("/api/gadgets/phones"))
                .withHeader("Content-Type", containing("application/json"))
                .withHeader("Authorization", equalTo("PutToken"))
                .withRequestBody(containing("\"Updated successfully\":true"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"method\":\"PUT\",\"status\":200}")
                        .withFixedDelay(100)));

        stubFor(delete(urlPathEqualTo("/api/gadgets/phones/Samsung"))
                .withHeader("Authorization", containing("DeleteToken"))
                .willReturn(aResponse()
                        .withStatus(204)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"method\":\"DELETE\",\"status\":200}")
                        .withUniformRandomDelay(250, 400)));
    }

    @Test
    public void testGet() {
        Response response = given()
                 .baseUri(url)
                 .queryParam("model", "15 Pro")
                 .header("Authorization", "GetToken")
                 .when()
                 .log().all()
                 .get("/api/gadgets/phones/1");

        assertTrue(response.getBody().asString().contains("15 Pro"));
        assertThat(findAll(getRequestedFor(urlPathEqualTo("/api/gadgets/phones/1")))).hasSizeGreaterThan(0);
        String authHeader = response.getHeader("Content-Type");
        assertThat(response.getHeader("Content-Type").equals("application/json"));
    }

    @Test
    public void testPost() {

        String jsonBody = "{ \"method\": \"POST\", \"status\": 201, \"id\": 2," +
                "\"phone\": \"Samsung\", \"Model\": \"Galaxy\", \"Condition\": \"Used\" }";

        given()
                .baseUri(url)
                .header("Authorization", "PostToken")
                .body(jsonBody)
                .when()
                .post("/api/gadgets/phones")
                .then()
                .statusCode(201);

        assertThat(findAll(getRequestedFor(urlPathEqualTo("/api/gadgets/phones")))).hasSizeLessThan(2);
    }

    @Test
    public void testPut() {

        given()
                .baseUri(url)
                .body("{\"Updated successfully\":true}")
                .when()
                .put("/api/gadgets/phones");

        assertThat(findAll(getRequestedFor(urlPathEqualTo("/api/gadgets/phones/1")))).hasSizeGreaterThanOrEqualTo(0);
    }

    @Test
    public void testDelete() {

        given()
                .baseUri(url)
                .when()
                .header("Authorization", "DeleteToken")
                .delete("/api/gadgets/phones/Samsung");

        List<LoggedRequest> requests = findAll(deleteRequestedFor(urlPathEqualTo("/api/gadgets/phones/Samsung"))
                .withHeader("Authorization", equalTo("DeleteToken")));
        assertThat(requests).isNotEmpty();


    }
}
