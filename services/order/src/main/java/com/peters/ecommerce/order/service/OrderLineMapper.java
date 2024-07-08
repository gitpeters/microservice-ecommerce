package com.peters.ecommerce.order.service;

import com.peters.ecommerce.order.dto.OrderLineRequest;
import com.peters.ecommerce.order.dto.OrderLineResponse;
import com.peters.ecommerce.order.model.Order;
import com.peters.ecommerce.order.model.OrderLine;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequest request) {
        return OrderLine.builder()
                .productId(request.productId())
                .order(Order.builder().id(request.orderId()).build())
                .quantity(request.purchaseQuantity())
                .build();
    }

    public OrderLineResponse fromOrderLine(OrderLine orderLine) {
        return OrderLineResponse.builder()
                .orderLineId(orderLine.getId())
                .quantity(orderLine.getQuantity())
                .build();
    }
}
