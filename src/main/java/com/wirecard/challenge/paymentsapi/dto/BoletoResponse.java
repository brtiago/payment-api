package com.wirecard.challenge.paymentsapi.dto;

public record BoletoResponse(
        Long id,
        String code,
        String expirationDate
) {
}
