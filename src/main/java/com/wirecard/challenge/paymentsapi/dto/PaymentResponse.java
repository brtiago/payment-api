package com.wirecard.challenge.paymentsapi.dto;

import com.wirecard.challenge.paymentsapi.model.PaymentMethod;
import com.wirecard.challenge.paymentsapi.model.PaymentStatus;

public record PaymentResponse(
        Long id,
        Double amount,
        PaymentMethod paymentMethod,
        PaymentStatus paymentStatus,
        BuyerResponse buyer,
        ClientResponse client
) {
}
