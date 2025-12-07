package com.wirecard.challenge.paymentsapi.dto;

import com.wirecard.challenge.paymentsapi.model.Buyer;
import com.wirecard.challenge.paymentsapi.model.Client;
import com.wirecard.challenge.paymentsapi.model.PaymentMethod;
import com.wirecard.challenge.paymentsapi.model.PaymentStatus;

public record PaymentResponse(
        Long id,
        Double amount,
        PaymentMethod paymentMethod,
        PaymentStatus paymentStatus,
        Buyer buyer,
        Client client
) {
}
