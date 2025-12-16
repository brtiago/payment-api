package com.wirecard.challenge.paymentsapi.dto;

import com.wirecard.challenge.paymentsapi.model.Boleto;
import org.springframework.stereotype.Component;

@Component
public class BoletoMapper {

    public Boleto toEntity(BoletoRequest request) {
        return Boleto.builder()
                .code(request.code())
                .amount(request.amount())
                .expirationDate(request.expirationDate())
                .build();
    }

    public BoletoResponse toResponse(Boleto boleto) {
        return new BoletoResponse(
                boleto.getId(),
                boleto.getCode(),
                boleto.getAmount(),
                boleto.getExpirationDate()
        );
    }
}
