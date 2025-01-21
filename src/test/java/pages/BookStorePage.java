package pages;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

public class BookStorePage {
    private final String baseUrl = "https://demoqa.com";

    public BookStorePage() {
        RestAssured.baseURI = baseUrl;
    }

    public Response listBooks() {
        return RestAssured.given()
                .header("Content-Type", "application/json")
                .get("/BookStore/v1/Books");
    }

    public Response rentBooks(String userID, String token, List<Map<String, String>> books) {
        Map<String, Object> requestBody = Map.of(
                "userId", userID,
                "collectionOfIsbns", books
        );

        return RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(requestBody)
                .post("/BookStore/v1/Books");
    }
}