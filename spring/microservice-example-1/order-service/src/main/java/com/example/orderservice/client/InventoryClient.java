package com.example.orderservice.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

public interface InventoryClient {

    // Also need to create a RestClient (RestClient, WebClient, RestTemplate)
    // RestTemplate is not favored; RestClient provides a better way
    // WebClient -- Need to add WebFlux
    // @CircuitBreaker name should match the name in the InventoryClient.java
    @GetExchange("/api/inventory")
    @CircuitBreaker(name = "inventory", fallbackMethod="fallbackMethod")
    @Retry(name="inventory")
    Boolean isInStock(@RequestParam String skuCode, @RequestParam Integer quantity);

    default boolean fallbackMethod(String code, Integer quantity, Throwable throwable ) {
        // Todo: Find a better way
        System.out.println(String.format("Cannot get inventory for skubcode {}, failure reason {}", code, throwable.getMessage()));
        return false;
    }

}


//@FeignClient( value = "inventory", url="${inventory.url}")
//public interface InventoryClient {
//
//    @RequestMapping(method = RequestMethod.GET, value="/api/inventory")
//    Boolean isInStock(@RequestParam String skuCode, @RequestParam Integer quantity);
//
//}
