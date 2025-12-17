package com.wirecard.challenge.paymentsapi.dto;

import com.wirecard.challenge.paymentsapi.model.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {
    private final BuyerMapper buyerMapper;
    private final ClientMapper clientMapper;

    public PaymentMapper(
            BuyerMapper buyerMapper,
            ClientMapper clientMapper
    ) {
        this.buyerMapper = buyerMapper;
        this.clientMapper = clientMapper;
    }

    public Payment toEntity(PaymentRequest request) {
        return Payment.builder()
                .amount(request.amount())
                .paymentMethod(request.paymentMethod())
                .status(request.paymentStatus())
                .buyer(request.buyer())
                .client(request.client())
                .build();
    }

    public PaymentResponse toResponse(Payment payment) {
        return new PaymentResponse(
                payment.getId(),
                payment.getAmount(),
                payment.getPaymentMethod(),
                payment.getStatus(),
                buyerMapper.toResponse(payment.getBuyer()),
                clientMapper.toResponse(payment.getClient())
        );
    }
}
