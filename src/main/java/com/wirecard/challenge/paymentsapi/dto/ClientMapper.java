package com.wirecard.challenge.paymentsapi.dto;

import com.wirecard.challenge.paymentsapi.model.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public Client toEntity(ClientRequest request) {
        return Client.builder()
                .name(request.name())
                .build();
    }

    public ClientResponse toResponse(Client client) {
        return new ClientResponse(
                client.getId(),
                client.getName()
        );
    }

}
