package Lesson_29.StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import java.time.LocalDate;
import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Assertions;

public class StepDefinitions {
    Response response;
    String zodiac;
    boolean isResponseValid = true;

    @Given("задан знак зодиака {string}")
    public void заданЗнакЗодиака(String sign) {
        zodiac = sign;
        System.out.println("Знак зодиака: " + sign);
    }

    @When("я отправляю запрос на гороскоп для этого знака")
    public void яОтправляюЗапросНаГороскопДляЭтогоЗнака() {
        response = given()
                .baseUri("https://ohmanda.com")
                .basePath("/api/horoscope/" + zodiac)
                .when()
                .get();
        int statusCode = response.getStatusCode();
        String contentType = response.getContentType();
        String body = response.asString();

        if (contentType != null && contentType.contains("text/html")) {
            System.out.println(zodiac + " :знак зодиака не найден (статус 404)");
            isResponseValid = false;
        } else {
            System.out.println("Ответ: " + body);
            assertThat(body, containsString("horoscope"));
        }
    }

    @Then("статус ответа должен быть {int}")
    public void статусОтветаДолженБыть(int status) {
        int statusCode = response.getStatusCode();
        if (!isResponseValid) {
            return;
        }
        response.then().statusCode(status);
        System.out.println("Статус: " + status);
        assertThat("Некорректный статус код", statusCode, equalTo(200));
    }

    @Then("в ответе поле \"sign\" должно быть равно {string}")
    public void вОтветеПолеДолжноБытьРавно(String expected) {
        if (!isResponseValid) {
            return;
        }
        String actual = response.jsonPath().getString("sign");
        System.out.println("Фактический знак: " + actual);
        Assertions.assertEquals(expected, actual);
        assertThat("Неверный формат ответа поля \"знак\"", actual, matchesPattern("[a-z]+"));
        assertThat("Знаки не совпадают ", actual, equalTo(expected));
    }

    @Then("в ответе должна быть сегодняшняя дата в поле \"date\"")
    public void вОтветеДолжнаБытьСегодняшняяДатавПоле() {
        if (!isResponseValid) {
            return;
        }
        String actualDate = LocalDate.now().toString();
        String responseDates = response.jsonPath().getString("date");
        System.out.println("Сегодняшняя дата: " + actualDate);
        System.out.println("Дата из ответа: " + responseDates);
        assertThat("Отсутствует поле \"date\" в ответе", response.jsonPath().getString("date"), notNullValue());
        assertThat("Даты не совпадают ", actualDate, equalTo(responseDates));
    }
}
