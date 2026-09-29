package com.rewards.api;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class RewardsApiTest {

    @Test
    void shouldGetAllRewards() {

        RestAssured.baseURI = "http://localhost:8081";

        given()
                .when()
                .get("/api/rewards")
                .then()
                .statusCode(200);
    }

//    @Test
//    void shouldGetRewardById() {
//
//        RestAssured.baseURI = "http://localhost:8081";
//
//        given()
//                .pathParam("id", 1)
//                .when()
//                .get("/api/rewards/{id}")
//                .then()
//                .statusCode(200)
//                .body("id", equalTo(1))
//                .body("name", equalTo("Amazon Voucher"))
//                .body("points", equalTo(5000));
//    }

    @Test
    void shouldGetRewardById() {

        RestAssured.baseURI = "http://localhost:8081";

        given()
                .pathParam("id", 1)
                .when()
                .get("/api/rewards/{id}")
                .then()
                .statusCode(200)
                .body("id", equalTo(1));
    }

    @Test
    void shouldCreateReward() {

        RestAssured.baseURI = "http://localhost:8081";

        String requestBody = """
        {
            "name": "Amya Voucher",
            "points": 5000
        }
        """;

        given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/rewards")
                .then()
                .statusCode(201)
                .body("name", equalTo("Amya Voucher"))
                .body("points", equalTo(5000));
    }
    @Test
    void shouldCreateAndRetrieveReward() {

        RestAssured.baseURI = "http://localhost:8081";

        String requestBody = """
        {
            "name":"Freebet Reward",
            "points": 500
        }
        """;

        int rewardId =
                given()
                        .contentType("application/json")
                        .body(requestBody)
                        .when()
                        .post("/api/rewards")
                        .then()
                        .statusCode(201)
                        .extract()
                        .path("id");

        given()
                .pathParam("id", rewardId)
                .when()
                .get("/api/rewards/{id}")
                .then()
                .statusCode(200)
                .body("id", equalTo(rewardId))
                .body("name", equalTo("Freebet Reward"))
                .body("points", equalTo(500));
    }

    @Test
    void shouldUpdateReward() {

        RestAssured.baseURI = "http://localhost:8081";

        // 1. Create a reward
        String createBody = """
        {
            "name": "Amazon 2 Voucher",
            "points": 500
        }
        """;

        int rewardId =
                given()
                        .contentType("application/json")
                        .body(createBody)
                        .when()
                        .post("/api/rewards")
                        .then()
                        .statusCode(201)
                        .extract()
                        .path("id");

        // 2. Update the reward
        String updateBody = """
        {
            "name": "Amazon Premium Voucher",
            "points": 1000
        }
        """;

        given()
                .contentType("application/json")
                .pathParam("id", rewardId)
                .body(updateBody)
                .when()
                .put("/api/rewards/{id}")
                .then()
                .statusCode(200)
                .body("id", equalTo(rewardId))
                .body("name", equalTo("Amazon Premium Voucher"))
                .body("points", equalTo(1000));

        // 3. Verify using GET
        given()
                .pathParam("id", rewardId)
                .when()
                .get("/api/rewards/{id}")
                .then()
                .statusCode(200)
                .body("name", equalTo("Amazon Premium Voucher"))
                .body("points", equalTo(1000));
    }

    @Test
    void shouldDeleteReward() {

        RestAssured.baseURI = "http://localhost:8081";

        // 1. Create a reward
        String requestBody = """
        {
            "name": "Temporary Reward",
            "points": 100
        }
        """;

        int rewardId =
                given()
                        .contentType("application/json")
                        .body(requestBody)
                        .when()
                        .post("/api/rewards")
                        .then()
                        .statusCode(201)
                        .extract()
                        .path("id");

        // 2. Delete the reward
        given()
                .pathParam("id", rewardId)
                .when()
                .delete("/api/rewards/{id}")
                .then()
                .statusCode(204);

        // 3. Verify that the reward no longer exists
        given()
                .pathParam("id", rewardId)
                .when()
                .get("/api/rewards/{id}")
                .then()
                .statusCode(404);
    }

}
