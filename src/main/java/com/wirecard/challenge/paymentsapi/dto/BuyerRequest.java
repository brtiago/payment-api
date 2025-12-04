package com.wirecard.challenge.paymentsapi.dto;

public record BuyerRequest(
        String email,
        String cpf,
        String nome
) {
}
