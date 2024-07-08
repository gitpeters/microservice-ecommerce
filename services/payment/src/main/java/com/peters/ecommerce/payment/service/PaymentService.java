package com.peters.ecommerce.payment.service;

import com.peters.ecommerce.payment.dto.PaymentRequest;

public interface PaymentService {
    String createPayment(PaymentRequest request);
}
