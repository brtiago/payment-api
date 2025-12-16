package com.wirecard.challenge.paymentsapi.dto;

import java.time.LocalDate;

public record BoletoResponse(
        Long id,
        String code,
        Double amount,
        LocalDate expirationDate
) {
}
