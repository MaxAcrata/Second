package ru.netology.rest;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

class MobileBankApiTestV4 {
    @Test
    void shouldReturnDemoAccounts() {
        // Given - When - Then
        // Предусловия
        given()
                .baseUri("http://localhost:9999/api/v1")
                // Выполняемые действия
                .when()
                .get("/demo/accounts")
                // Проверки
                .then()
                // Включаем логирование ответа
                .log().all()
                .statusCode(200)
        // Проверка JSON схемы
                .body(matchesJsonSchemaInClasspath("accounts.schema.json"))
                // Дополнительные проверки валюты
                .body("currency", everyItem(anyOf(equalTo("RUB"), equalTo("USD"))));
    }
}
