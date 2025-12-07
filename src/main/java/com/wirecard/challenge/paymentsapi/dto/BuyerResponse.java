package com.wirecard.challenge.paymentsapi.dto;

public record BuyerResponse(
        String email,
        String cpf,
        String nome
) {
}
