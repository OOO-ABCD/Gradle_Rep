package Lesson_28.Step_definitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.List;
import java.util.Map;

public class StepDefinitions1 {
    @Given("пользователь {string} с email {string}")
    public void пользователь_и_его_email(String userName, String userEmail) {
        System.out.println("пользователь: " + userName + " email '" + userEmail);
    }

    @Given("в корзине есть следующие товары")
    public void в_корзине_есть_следующие_товары(DataTable DataTable) {
        List<Map<String, String>> goods = DataTable.asMaps(String.class, String.class);
        System.out.println("Товары в корзине:");
        for (Map<String, String> item : goods) {
            System.out.println("Название: " + item.get("Название"));
            System.out.println("Цена: " + item.get("Цена"));
            System.out.println("Количество: " + item.get("Количество"));
            System.out.println("---------------");
        }
    }

    @Given("выбран способ оплаты {string}")
    public void выбран_способ_оплаты(String paymentMethod) {
        System.out.println("способ оплаты: " + paymentMethod);
    }

    @Given("выбран способ доставки {string}")
    public void выбран_способ_доставки(String deliveryMethod) {
        System.out.println("способ доставки: " + deliveryMethod);
    }

    @When("пользователь подтверждает заказ")
    public void пользователь_подтверждает_заказ (){
        System.out.println("пользователь подтвердил заказ");
    }

    @Then("система отправляет письмо с подтверждением на email {string}")
    public void система_отправляет_письмо_на_email(String userEmail) {
        System.out.println("письмо с подтверждением отправлено на email " + userEmail);
    }

    @Then("формирует заказ с номером {string}")
    public void формируется_заказ_с_номером(String orderId) {
        System.out.println("номер заказа " + orderId);
    }

    @Then("отображает сообщение: {string}")
    public void отображается_сообщение(String messageText) {
        System.out.println("отображается сообщение " + messageText);
        System.out.println("===============");
    }
}
