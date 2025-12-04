package com.wirecard.challenge.paymentsapi.dto;

import jakarta.validation.constraints.NotBlank;

public record ClientRequest(
        @NotBlank(message = "Nome do cliente é obrigatório")
        String name
) {
}
