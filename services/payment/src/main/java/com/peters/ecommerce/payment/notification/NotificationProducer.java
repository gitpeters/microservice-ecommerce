package com.peters.ecommerce.payment.notification;

import com.peters.ecommerce.payment.dto.PaymentNotificationRequest;
import com.peters.ecommerce.payment.exceptions.PaymentProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationProducer {
    private final KafkaTemplate<String, PaymentNotificationRequest> kafkaTemplate;

    public void sendNotification(PaymentNotificationRequest request){
        log.info("Sending notification to payment notification with body: {}", request);
        Message<PaymentNotificationRequest> message = MessageBuilder
                .withPayload(request)
                .setHeader(KafkaHeaders.TOPIC, "payment-topic")
                .build();

        try {
            kafkaTemplate.send(message);
        }catch (Exception e){
            throw new PaymentProcessingException("Error occurred while sending payment notification : "+e.getMessage());
        }
    }
}
