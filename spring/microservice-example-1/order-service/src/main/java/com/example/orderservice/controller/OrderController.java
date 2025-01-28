package com.example.orderservice.controller;

import com.example.orderservice.dto.OrderRequestDto;
import com.example.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String receivedOrderRequest(@RequestBody OrderRequestDto orderRequestDto) {
        orderService.placeOrder(orderRequestDto);
        log.trace("Order has been successfully ordered.");
        return "Order Placed Successfully";
    }

    private void logOrder(OrderRequestDto orderRequestDto) {
        if ( log.isInfoEnabled()) {
            log.debug(String.format("Received a request with size %d and the first skuCode is %s",
                    orderRequestDto.getOrderLineItemsDtoList().size(),
                    orderRequestDto.getOrderLineItemsDtoList().getFirst().getSkuCode()));
        } else if ( log.isDebugEnabled()) {
            log.debug(String.format("Received a request with size %d and the skuCode(s) are %s",
                    orderRequestDto.getOrderLineItemsDtoList().size()),
                    orderRequestDto.getOrderLineItemsDtoList().stream().toString());
        }

        orderRequestDto.getOrderLineItemsDtoList().stream().limit(3).forEach( item -> {
            System.out.println(item.toString());
        });
    }





}
