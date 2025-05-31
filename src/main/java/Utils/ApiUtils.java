package Utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class ApiUtils {

    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site/api";

    public static String createUser(String email, String password, String name) {
        Map<String, Object> user = new HashMap<>();
        user.put("email", email);
        user.put("password", password);
        user.put("name", name);

        Response response = RestAssured.given()
                .contentType("application/json")
                .body(user)
                .post(BASE_URL + "/auth/register");

        if (response.statusCode() == 200) {
            return response.jsonPath().getString("accessToken");
        } else {
            throw new RuntimeException("Failed to create user via API");
        }
    }

    public static void deleteUser(String accessToken) {
        RestAssured.given()
                .header("Authorization", accessToken)
                .delete(BASE_URL + "/auth/user");
    }
}

