package com.peters.ecommerce.notification.service;

import lombok.Getter;

@Getter
public enum EmailTemplates {
    PAYMENT_CONFIRMATION("payment-confirmation.html", "Payment Successfully Processed"),
    ORDER_CONFIRMATION("order-confirmation.html", "Order Confirmation");

    private final String template;

    private final String subject;

    EmailTemplates(final String template, final String subject) {
        this.template = template;
        this.subject = subject;
    }
}
