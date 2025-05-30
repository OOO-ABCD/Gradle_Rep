# language: ru

Функционал: Оформление заказа в интернет-магазине

  Структура сценария: Оформление заказа через корзину
    Дано пользователь "<user_name>" с email "<user_mail>"
    И в корзине есть следующие товары
      | Название | Цена  | Количество |
      | Книга    | 500   | 2          |
      | Ноутбук  | 50000 | 1          |
    И выбран способ оплаты "<payment_method>"
    И выбран способ доставки "<delivery_method>"

    Когда пользователь подтверждает заказ
    Тогда система отправляет письмо с подтверждением на email "<user_email>"
    И формирует заказ с номером "<order_id>"
    И отображает сообщение: "<confirmation_message>"

    Примеры:
      | user_name | user_email    | payment_method | delivery_method | order_id | confirmation_message
      | Иван      | ivan@mail.ru  | Карта          | Курьер          | #1001    | Заказ #1001 оформлен! Ожидайте доставку
      | Анна      | anna@mail.com | PayPal         | Самовывоз       | #1002    | Заказ #1002 готов к выдаче