package pages;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class AccountPage {
    private final String baseUrl = "https://demoqa.com";
    private String userID;
    private String token;

    public AccountPage() {
        RestAssured.baseURI = baseUrl;
    }

    public Response createUser(String username, String password) {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("userName", username);
        requestBody.put("password", password);

        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post("/Account/v1/User");

        if (response.getStatusCode() == 201) {
            userID = response.jsonPath().getString("userID");
        }

        return response;
    }

    public Response generateToken(String username, String password) {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("userName", username);
        requestBody.put("password", password);

        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post("/Account/v1/GenerateToken");

        if (response.getStatusCode() == 200) {
            token = response.jsonPath().getString("token");
        }

        return response;
    }

    public Response checkAuthorization(String username, String password) {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("userName", username);
        requestBody.put("password", password);

        return RestAssured.given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post("/Account/v1/Authorized");
    }

    public Response getUserDetails() {
        return RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .get("/Account/v1/User/" + userID);
    }

    public String getUserID() {
        return userID;
    }

    public String getToken() {
        return token;
    }
}