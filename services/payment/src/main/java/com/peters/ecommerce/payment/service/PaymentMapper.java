package com.peters.ecommerce.payment.service;

import com.peters.ecommerce.payment.dto.PaymentRequest;
import com.peters.ecommerce.payment.model.Payment;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class PaymentMapper {
    private static final String CHAR_SET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public Payment toPayment(PaymentRequest request) {
        return Payment.builder()
                .orderId(request.orderId())
                .amount(request.amount())
                .paymentMethod(request.paymentMethod())
                .paymentReference(generatePaymentReference(10))
                .build();
    }

    public static String generatePaymentReference(int length) {
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder randomString = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(CHAR_SET.length());
            randomString.append(CHAR_SET.charAt(randomIndex));
        }
        return randomString.toString();
    }
}


