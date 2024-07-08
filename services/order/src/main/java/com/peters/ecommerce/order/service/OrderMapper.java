package com.peters.ecommerce.order.service;

import com.peters.ecommerce.order.dto.OrderRequest;
import com.peters.ecommerce.order.dto.OrderResponse;
import com.peters.ecommerce.order.model.Order;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class OrderMapper {
    private static final String CHAR_SET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int REFERENCE_LENGTH = 10;
    public Order toOrder(OrderRequest request) {
        return Order.builder()
                .paymentMethod(request.paymentMethod())
                .customerId(request.customerId())
                .totalAmount(request.amount())
                .reference(generateOrderReference(REFERENCE_LENGTH))
                .build();
    }

    public static String generateOrderReference(int length) {
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder randomString = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(CHAR_SET.length());
            randomString.append(CHAR_SET.charAt(randomIndex));
        }
        return randomString.toString();
    }

    public OrderResponse fromOrder(Order order) {
        return OrderResponse.builder()
                .orderId(order.getId())
                .reference(order.getReference())
                .totalPrice(order.getTotalAmount())
                .paymentMethod(order.getPaymentMethod())
                .customerId(order.getCustomerId())
                .build();
    }
}
