package com.example.inventoryservice;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.testcontainers.containers.MySQLContainer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InventoryServiceApplicationTests {
    @ServiceConnection
    static MySQLContainer mySqlContainer = new MySQLContainer("mysql:8.3.0")
            .withUsername("root")
            .withPassword("mysql")
            .withDatabaseName("inventory_service");

    @LocalServerPort
    private Integer port;

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    static {
        mySqlContainer.start();
    }

    @Test
    void shouldReadInventory() {
        // System.out.println("Dataname = " + mySqlContainer.getDatabaseName());
        var response = RestAssured.given()
                .when()
                .get("/api/inventory?skuCode=iphone15&quantity=1")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response().as(Boolean.class);
        assertTrue(response);
    }

    @Test
    void shouldNotReadInventory() {
        var negativeResponse = RestAssured.given()
                .when()
                .get("/api/inventory?skuCode=iphone15&quantity=1000")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response().as(Boolean.class);
        assertFalse(negativeResponse);
    }

}
