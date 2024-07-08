package com.peters.ecommerce.order.service;

import com.peters.ecommerce.order.dto.OrderRequest;
import com.peters.ecommerce.order.dto.OrderResponse;

import java.util.List;

public interface OrderService {
    Integer createOrder(OrderRequest request);

    List<OrderResponse> findAllOrders();

    OrderResponse findOrderById(Integer orderId);
}
