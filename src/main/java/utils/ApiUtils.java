package utils;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ApiUtils {

    private static final String BASE_URL_API = "https://stellarburgers.nomoreparties.site/api";

    @Step("Создание пользователя через API: {user.email}")
    public static String createUser(UserModel user) {
        Response response = RestAssured.given()
                .contentType("application/json")
                .body(user)
                .post(BASE_URL_API + "/auth/register");

        if (response.statusCode() == 200) {
            return response.jsonPath().getString("accessToken");
        } else {
            System.err.println("Status code: " + response.statusCode());
            System.err.println("Response body: " + response.getBody().asString());
            throw new RuntimeException("Failed to create user via API");
        }
    }

    @Step("Логин пользователя через API и получение токена: {email}")
    public static String loginUser(String email, String password) {
        UserModel loginUser = new UserModel(email, password, null); // имя не нужно для логина

        Response response = RestAssured.given()
                .contentType("application/json")
                .body(loginUser) // сериализация в JSON
                .post(BASE_URL_API + "/auth/login");

        if (response.statusCode() == 200) {
            return response.jsonPath().getString("accessToken");
        } else {
            System.err.println("Status code: " + response.statusCode());
            System.err.println("Response body: " + response.getBody().asString());
            throw new RuntimeException("Failed to login user via API");
        }
    }

    @Step("Удаление пользователя через API")
    public static void deleteUser(String accessToken) {
        RestAssured.given()
                .header("Authorization", accessToken)
                .delete(BASE_URL_API + "/auth/user");
    }
}



