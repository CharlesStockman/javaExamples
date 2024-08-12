package com.example.apigateway.routes;

import org.springframework.cloud.gateway.server.mvc.filter.CircuitBreakerFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.function.*;

import java.net.URI;

import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.setPath;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class Routes {
    @Bean
    public RouterFunction<ServerResponse> productServiceRoute() {

        // Example of Functional Endpoints -- Used to route and handle requests and contracts are designed to be immutable
        // Alternative to the annotation model ( define get, post mappings etc... )
        // route(RequestPredicates.path could have been written as RequestPredicates.GET
        return GatewayRouterFunctions.route("product_service")
                .route(RequestPredicates.path("/api/product"), HandlerFunctions.http("http://localhost:8080"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("productServiceCircuitBreak", URI.create("forwared:/fallbackRoute")))
                .build();

        // Could have done -- Fix later when refactoring is done
        // RouterFunctions<ServerResponse> route2 = route().GET("/api/product", accept(APPLICATION_JSON), handler());
    }

    @Bean
    public RouterFunction<ServerResponse> orderServiceRoute() {

        // Example of Functional Endpoints -- Used to route and handle requests and contracts are designed to be immutable
        // Alternative to the annotation model ( define get, post mappings etc... )
        // route(RequestPredicates.path could have been written as RequestPredicates.GET
        return GatewayRouterFunctions.route("order_service").
                route(RequestPredicates.path("/api/order"), HandlerFunctions.http("http://localhost:8081"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("orderServiceCircuitBreak", URI.create("forwared:/fallbackRoute")))
                .build();

        // Could have done -- Fix later when refactoring is done
        // RouterFunctions<ServerResponse> route2 = route().GET("/api/product", accept(APPLICATION_JSON), handler());
    }

    @Bean
    public RouterFunction<ServerResponse> InventoryServiceRoute() {

        // Example of Functional Endpoints -- Used to route and handle requests and contracts are designed to be immutable
        // Alternative to the annotation model ( define get, post mappings etc... )
        // route(RequestPredicates.path could have been written as RequestPredicates.GET
        return GatewayRouterFunctions.route("inventory_service").
                route(RequestPredicates.path("/api/inventory"), HandlerFunctions.http("http://localhost:8082"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("inventoryServiceCircuitBreak", URI.create("forwared:/fallbackRoute")))
                .build();

        // Could have done -- Fix later when refactoring is done
        // RouterFunctions<ServerResponse> route2 = route().GET("/api/product", accept(APPLICATION_JSON), handler());
    }

    //////////////////////////////////////////
    // Allow the gateway to display the documentation for each the REST Services ( inventory, Order, Product)
    /////////////////////////////////////////

    // Filter will change the url from http://localhost:8080/aggregate/product-service/v1/api-docs to http://localhost:8080/api-docs
    @Bean
    public RouterFunction<ServerResponse> productServiceSwaggerRoute() {
        return GatewayRouterFunctions.route("product_service_manager")
                .route(RequestPredicates.path("/aggregate/product-service/v3/api-docs"), HandlerFunctions.http("http://localhost:8080"))
                .filter(setPath("/api-docs"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("productServiceSwaggerCircuitBreak", URI.create("forwared:/fallbackRoute")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> orderServiceSwaggerRoute() {
        return GatewayRouterFunctions.route("order_service_manager")
                .route(RequestPredicates.path("/aggregate/order-service/v3/api-docs"), HandlerFunctions.http("http://localhost:8080"))
                .filter(setPath("/api-docs"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("OrderServiceSwaggerCircuitBreak", URI.create("forwared:/fallbackRoute")))
                .build();
    }

    public RouterFunction<ServerResponse> inventoryServiceSwaggerRoute() {
        return GatewayRouterFunctions.route("inventory_service_manager")
                .route(RequestPredicates.path("/aggregate/inventory-service/v3/api-docs"), HandlerFunctions.http("http://localhost:8080"))
                .filter(setPath("/api-docs"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("InventoryServiceSwaggerCircuitBreak", URI.create("forwared:/fallbackRoute")))
                .build();
    }

    /** @TODO understand why this route instead fo GatewayRouterFunctions */
    @Bean
    public RouterFunction<ServerResponse> fallbackRoute() {
        return GatewayRouterFunctions.route("fallbackRoute")
                .GET("/fallbackRoute", request -> ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE).body("Service Unavailable"))
                .build();
    }
}