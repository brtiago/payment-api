package com.wirecard.challenge.paymentsapi.dto;

public record BoletoRequest(
        String code,
        String expirationDate
) {
}
