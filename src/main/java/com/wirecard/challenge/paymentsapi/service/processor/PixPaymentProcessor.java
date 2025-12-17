package com.wirecard.challenge.paymentsapi.service.processor;

import com.wirecard.challenge.paymentsapi.dto.PaymentMapper;
import com.wirecard.challenge.paymentsapi.dto.PaymentRequest;
import com.wirecard.challenge.paymentsapi.dto.PaymentResponse;
import com.wirecard.challenge.paymentsapi.model.Payment;
import com.wirecard.challenge.paymentsapi.model.PaymentMethod;
import com.wirecard.challenge.paymentsapi.model.PaymentStatus;
import com.wirecard.challenge.paymentsapi.repository.PaymentRepository;
import org.springframework.stereotype.Component;

@Component
public class PixPaymentProcessor implements PaymentProcessor {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    public PixPaymentProcessor(
            PaymentRepository paymentRepository,
            PaymentMapper paymentMapper
    ) {
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
    }

    @Override
    public PaymentResponse process(PaymentRequest request) {
        String qrCode = gerarQRCode(request);

        Payment payment = Payment.builder()
                .amount(request.amount())
                .paymentMethod(PaymentMethod.PIX)
                .status(PaymentStatus.CREATED)
                .buyer(request.buyer())
                .client(request.client())
                .build();

        Payment savedPayment = paymentRepository.save(payment);
        return paymentMapper.toResponse(savedPayment);
    }

    @Override
    public PaymentMethod getSupportedMethod() {
        return PaymentMethod.PIX;
    }

    private String gerarQRCode(PaymentRequest request) {
        return "00020126580014br.gov.bcb.pix...";
    }
}
