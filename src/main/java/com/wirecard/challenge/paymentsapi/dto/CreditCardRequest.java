package com.wirecard.challenge.paymentsapi.dto;

import com.wirecard.challenge.paymentsapi.model.Holder;

import java.time.LocalDate;

public record CreditCardRequest(
        String brand,
        Holder holder,
        String cardNumber,
        LocalDate expirationDate,
        String cvv
) {
}
