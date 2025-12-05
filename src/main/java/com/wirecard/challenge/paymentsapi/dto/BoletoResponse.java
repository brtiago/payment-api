package com.wirecard.challenge.paymentsapi.dto;

import com.wirecard.challenge.paymentsapi.model.Boleto;

public record BoletoResponse(
        Long id,
        String code,
        String expirationDate
) {
    public static BoletoResponse fromEntity(Boleto entidade) {
        return new BoletoResponse(
                entidade.getId(),
                entidade.getCode(),
                entidade.getExpirationDate()
        );
    }
}
