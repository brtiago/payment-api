package com.wirecard.challenge.paymentsapi.dto;

import com.wirecard.challenge.paymentsapi.model.Buyer;

public record BuyerResponse(
        String email,
        String cpf,
        String nome
) {
    public static BuyerResponse fromEntity(Buyer entidade) {
        return new BuyerResponse(
                entidade.getEmail(),
                entidade.getCpf(),
                entidade.getNome()
        );
    }
}
