package com.peters.ecommerce.payment.service;

import com.peters.ecommerce.payment.dto.PaymentNotificationRequest;
import com.peters.ecommerce.payment.dto.PaymentRequest;
import com.peters.ecommerce.payment.notification.NotificationProducer;
import com.peters.ecommerce.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository repository;
    private final PaymentMapper mapper;
    private final NotificationProducer notificationProducer;

    @Override
    public String createPayment(PaymentRequest request) {
        var payment = repository.save(mapper.toPayment(request));
        PaymentNotificationRequest notificationRequest = PaymentNotificationRequest.builder()
                .orderReference(request.orderReference())
                .amount(request.amount())
                .paymentMethod(request.paymentMethod())
                .customerFirstname(request.customer().firstname())
                .customerLastname(request.customer().lastname())
                .customerEmail(request.customer().email())
                .build();
        notificationProducer.sendNotification(notificationRequest);
        return payment.getPaymentReference();
    }
}
