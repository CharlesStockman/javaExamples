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
    public String placeOrder(@RequestBody OrderRequestDto orderRequestDto) {
        log.trace("Charles Stockman: Entering order-service with orderRequest " + orderRequestDto.toString());
        orderService.placeOrder(orderRequestDto);
        log.trace("Charles Stockman: Order has been successfully ordered.");
        return "Order Placed Successfully";
    }

}
