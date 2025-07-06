package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Map;

public class ApiBaseFun {
    private static final String BASE_URI = "https://stellarburgers.nomoreparties.site/api";
    static { RestAssured.baseURI = BASE_URI; }

    public static String createUser(String email, String password, String name) {
        Response resp = RestAssured.given()
                .contentType("application/json")
                .body(Map.of("email", email, "password", password, "name", name))
                .post("/auth/register");
        return resp.jsonPath().getString("accessToken");
    }

    public static String loginUser(String email, String password) {
        Response resp = RestAssured.given()
                .contentType("application/json")
                .body(Map.of("email", email, "password", password))
                .post("/auth/login");
        return resp.jsonPath().getString("accessToken");
    }
    public static void deleteUser(String token) {
        RestAssured.given()
                .header("Authorization", token)
                .delete("/auth/user");
    }
}
