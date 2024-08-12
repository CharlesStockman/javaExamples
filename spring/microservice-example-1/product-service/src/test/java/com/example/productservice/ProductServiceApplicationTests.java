package com.example.productservice;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MongoDBContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {

    // Using the same image ( mongo:7.0.5) used in docker-compose.yaml
    // Using ServiceConnection don't need to specify the URI
    @ServiceConnection
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.5");

    static {
        mongoDBContainer.start();
    }

    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test
    void contextLoads() {
    }

    @Test
    void shouldCreateProduct() {

        String requestBody = """
            {
                "name":"Iphone 13",
                "description":"iphone 13",
                "price":1200
            }
        """;

        RestAssured.given().contentType("application/json").body(requestBody)
                .when()
                .post("/api/product")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("id", Matchers.notNullValue())
                .body("name",  Matchers.equalTo("Iphone 15"))
                .body( "price", Matchers.equalTo(1200));
    }
}
