package com.peters.ecommerce.notification.kafka;

import com.peters.ecommerce.notification.kafka.order.OrderConfirmation;
import com.peters.ecommerce.notification.kafka.payment.PaymentConfirmation;
import com.peters.ecommerce.notification.model.Notification;
import com.peters.ecommerce.notification.repository.NotificationRepository;
import com.peters.ecommerce.notification.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.peters.ecommerce.notification.model.NotificationType.ORDER_CONFIRMATION;
import static com.peters.ecommerce.notification.model.NotificationType.PAYMENT_CONFIRMATION;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {
    private final EmailService emailService;
    private final NotificationRepository repository;

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) {
        log.info("Consuming message from payment-topic: {}", paymentConfirmation);
        Notification notification = Notification.builder()
                .notificationType(PAYMENT_CONFIRMATION)
                .notificationDate(LocalDateTime.now())
                .paymentConfirmation(paymentConfirmation)
                .build();
        repository.save(notification);


        emailService.sendPaymentSuccessEmail(
                paymentConfirmation.customerEmail(),
                paymentConfirmation.customerFirstname(),
                paymentConfirmation.orderReference(),
                paymentConfirmation.amount()
        );
    }

    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmationNotification(OrderConfirmation orderConfirmation) {
        log.info("Consuming message from order-topic: {}", orderConfirmation);
        Notification notification = Notification.builder()
                .notificationType(ORDER_CONFIRMATION)
                .notificationDate(LocalDateTime.now())
                .orderConfirmation(orderConfirmation)
                .build();
        repository.save(notification);

        emailService.sendOrderConfirmationEmail(
                orderConfirmation.customer().email(),
                orderConfirmation.customer().firstname(),
                orderConfirmation.orderReference(),
                orderConfirmation.products()
        );
    }
}
