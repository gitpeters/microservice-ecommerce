package com.peters.ecommerce.order.kafka;

import com.peters.ecommerce.order.dto.OrderConfirmation;
import com.peters.ecommerce.order.exceptions.ProducerException;
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
public class OrderProducer {
    private final KafkaTemplate<String, OrderConfirmation> kafkaTemplate;

    public void sendOrderConfirmation(OrderConfirmation order){
        try{
            log.info("Sending Order confirmation: {}", order.orderReference());
            Message<OrderConfirmation> message = MessageBuilder
                    .withPayload(order)
                    .setHeader(KafkaHeaders.TOPIC, "order-topic") // this headerValue should be the same name set in the Kafka topic configuration
                    .build();
            kafkaTemplate.send(message);
        }catch (Exception e){
            throw new ProducerException("Error sending Order confirmation:"+e.getMessage());
        }
    }
}
