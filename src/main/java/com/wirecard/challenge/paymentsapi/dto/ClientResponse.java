package com.wirecard.challenge.paymentsapi.dto;

import com.wirecard.challenge.paymentsapi.model.Client;

public record ClientResponse(
        Long id,
        String name
) {
    public static ClientResponse fromEntity (Client entidade) {
        return new ClientResponse(
                entidade.getId(),
                entidade.getName()
                );
    }
}
