package com.example.orderservice;

import com.example.orderservice.dto.OrderLineItemsDto;
import com.example.orderservice.dto.OrderRequestDto;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderControllerTest {

    @Test
    public void testLogOrderWithOneItem() {

        OrderLineItemsDto order1 = new OrderLineItemsDto(null, "1", new BigDecimal("2.5"), 20);
        List<OrderLineItemsDto> orders = new ArrayList<OrderLineItemsDto>();
        orders.add(order1);
        OrderRequestDto request = new OrderRequestDto(orders);
    }

    private void generateData(int creationAmount) {

        OrderRequestDto orderRequestDTo = new OrderRequestDto();
        orderRequestDTo.setOrderLineItemsDtoList(new ArrayList<OrderLineItemsDto>());

        Long id_value = null;
        int skuCodeInt = 1;
        float price = 2.5F;
        int quantity = 20;

        List<OrderLineItemsDto> order = new ArrayList<>();
        for ( int index = 0; index < creationAmount; index++ ) {
           orderRequestDTo.getOrderLineItemsDtoList().add(
                   new OrderLineItemsDto(
                           id_value,
                           Integer.toString(skuCodeInt),
                           new BigDecimal(Float.toString(price)),
                           quantity));

           skuCodeInt++;
           price = price + 2.5F;
           quantity = quantity + 20;
        }


    }
}
