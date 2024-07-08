package com.peters.ecommerce.order.service;

import com.peters.ecommerce.order.client.CustomerClient;
import com.peters.ecommerce.order.client.PaymentClient;
import com.peters.ecommerce.order.client.ProductClient;
import com.peters.ecommerce.order.dto.*;
import com.peters.ecommerce.order.exceptions.BusinessException;
import com.peters.ecommerce.order.exceptions.OrderNotFoundException;
import com.peters.ecommerce.order.kafka.OrderProducer;
import com.peters.ecommerce.order.model.Order;
import com.peters.ecommerce.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderRepository repository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final PaymentClient paymentClient;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;

    @Override
    public Integer createOrder(OrderRequest request) {
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(()-> new BusinessException(String.format("Cannot create order. No customer exists with the provided ID: %s ", request.customerId())));
        List<PurchaseResponse> purchasedProducts = this.productClient.purchaseProduct(request.products());
        var order = this.repository.save(mapper.toOrder(request));
        // persist the order-lines
        for (PurchaseRequest purchaseRequest : request.products()){
            this.orderLineService.saveOrderLine(
                    OrderLineRequest.builder()
                            .orderId(order.getId())
                            .productId(purchaseRequest.productId())
                            .purchaseQuantity(purchaseRequest.quantity())
                            .build()
            );
        }
        // send payment confirmation notification
        PaymentRequest paymentRequest = PaymentRequest.builder()
                .orderId(order.getId())
                .orderReference(order.getReference())
                .amount(request.amount())
                .paymentMethod(request.paymentMethod())
                .customer(customer)
                .build();
        paymentClient.requestOrderPayment(paymentRequest);
        // start payment process
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        order.getReference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchasedProducts
                )
        );
        return order.getId();
    }

    @Override
    public List<OrderResponse> findAllOrders() {
        return repository.findAll().stream()
                .map(mapper::fromOrder).toList();
    }

    @Override
    public OrderResponse findOrderById(Integer orderId) {
        return repository.findById(orderId)
                .map(mapper::fromOrder)
                .orElseThrow(()-> new OrderNotFoundException(String.format("Cannot find order with ID: %d", orderId)));
    }
}
