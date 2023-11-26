package com.example.orderservice.service;

import com.example.orderservice.dto.InventoryResponse;
import com.example.orderservice.dto.OrderLineItemsDto;
import com.example.orderservice.dto.OrderRequestDto;
import com.example.orderservice.model.Order;
import com.example.orderservice.model.OrderLineItems;
import com.example.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;

    private final WebClient webClient;

    public void placeOrder(OrderRequestDto orderRequestDto) {

        log.trace("An order has been replaced for orderRequest " + orderRequestDto.toString());

        Order order = this.orderLineMapDtoToOrder(orderRequestDto );

        List<String> skuCodes = order.getOrderLineItemsList().stream().map(orderLineItem -> orderLineItem.getSkuCode()).toList();

        // Example of Synchronous Communication between web services
        // Create a list of sku ccodes and whether they are in 
        InventoryResponse[] result =
                webClient.get().
                        uri("http://localhost:8082/api/inventory", uriBuilder -> uriBuilder.queryParam("sku-code", skuCodes).build()).
                        retrieve().bodyToMono(InventoryResponse[].class).
                        block();

        boolean allProductsInStock = Arrays.stream(result).allMatch(InventoryResponse::isInStock);
        if ( allProductsInStock  == false )
            throw new IllegalArgumentException("Product is not in stock");

        orderRepository.save(order);
        log.trace("Charles Stockman: Placed an order for orderLineItems -- ", order.toString());

    }


    private Order orderLineMapDtoToOrder(OrderRequestDto orderRequestDto ) {
        // Mapping the DTO to the Model
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setOrderLineItemsList(orderRequestDto.getOrderLineItemsDtoList().stream().map(this::mapToDto).toList());
        if ( log.isTraceEnabled()) {
            log.trace("Charles Stockman : The model order is " + order.toString());
        }

        return order;
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());

        log.info("original" + orderLineItemsDto);
        log.info("mapToDto: " + orderLineItemsDto.toString());

        return orderLineItems;

    }
}
