package com.peters.ecommerce.notification.service;

import com.peters.ecommerce.notification.kafka.order.Product;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.peters.ecommerce.notification.service.EmailTemplates.ORDER_CONFIRMATION;
import static com.peters.ecommerce.notification.service.EmailTemplates.PAYMENT_CONFIRMATION;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.mail.javamail.MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Async
    public void sendPaymentSuccessEmail(String destination,
                                        String customerName,
                                        String orderReference,
                                        BigDecimal amount
    ) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, MULTIPART_MODE_MIXED_RELATED, UTF_8.name());
            mimeMessageHelper.setFrom("depitaztech@gmail.com");

            final String templateName = PAYMENT_CONFIRMATION.getTemplate();

            Map<String, Object> variables = new HashMap<>();
            variables.put("customerName", customerName);
            variables.put("amount", amount);
            variables.put("orderReference", orderReference);
            Context context = new Context();
            context.setVariables(variables);
            mimeMessageHelper.setSubject(PAYMENT_CONFIRMATION.getSubject());

            // process template
            String htmlTemplate = templateEngine.process(templateName, context);
            mimeMessageHelper.setText(htmlTemplate, true);
            mimeMessageHelper.setTo(destination);
            mailSender.send(mimeMessage);
            log.info("INFO - Email successfully sent to: {} ",destination);
        } catch (MessagingException e) {
            log.warn("ERROR - Cannot send email to: {} ",destination);
            throw new RuntimeException(e.getMessage());
        }

    }

    @Async
    public void sendOrderConfirmationEmail(String destination,
                                        String customerName,
                                        String orderReference,
                                          List<Product> products
    ) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, MULTIPART_MODE_MIXED_RELATED, UTF_8.name());
            mimeMessageHelper.setFrom("depitaztech@gmail.com");

            final String templateName = ORDER_CONFIRMATION.getTemplate();
            BigDecimal totalPrice = products.stream()
                    .map(Product::price)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            Map<String, Object> variables = new HashMap<>();
            variables.put("customerName", customerName);
            variables.put("totalAmount", totalPrice);
            variables.put("products", products);
            variables.put("orderReference", orderReference);
            Context context = new Context();
            context.setVariables(variables);
            mimeMessageHelper.setSubject(ORDER_CONFIRMATION.getSubject());

            // process template
            String htmlTemplate = templateEngine.process(templateName, context);
            mimeMessageHelper.setText(htmlTemplate, true);
            mimeMessageHelper.setTo(destination);
            mailSender.send(mimeMessage);
            log.info("INFO - Email successfully sent to: {} ",destination);
        } catch (MessagingException e) {
            log.warn("ERROR - Cannot send email to: {} ",destination);
            throw new RuntimeException(e.getMessage());
        }

    }
}
