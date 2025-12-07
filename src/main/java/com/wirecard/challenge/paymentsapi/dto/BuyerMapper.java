package com.wirecard.challenge.paymentsapi.dto;

import com.wirecard.challenge.paymentsapi.model.Buyer;
import org.springframework.stereotype.Component;

@Component
public class BuyerMapper {

    public Buyer toEntity(BuyerRequest request) {
        return Buyer.builder()
                .email(request.email())
                .cpf(request.cpf())
                .nome(request.nome())
                .build();
    }

    public BuyerResponse toResponse(Buyer buyer) {
        return new BuyerResponse(
                buyer.getEmail(),
                buyer.getCpf(),
                buyer.getNome()
        );
    }
}
