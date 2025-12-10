package com.wirecard.challenge.paymentsapi.dto;

import com.wirecard.challenge.paymentsapi.model.CreditCard;
import org.springframework.stereotype.Component;

@Component
public class CreditCardMapper {

    public CreditCard toEntity(CreditCardRequest request) {
        return CreditCard.builder()
                .brand(request.brand())
                .holder(request.holder())
                .cardNumber(request.cardNumber())
                .expirationDate(request.expirationDate())
                .cvv(request.cvv())
                .build();
    }

    public CreditCardResponse toResponse(CreditCard creditCard) {
        return new CreditCardResponse(
                creditCard.getBrand(),
                creditCard.getHolder(),
                creditCard.getCardNumber(),
                creditCard.getExpirationDate(),
                creditCard.getCvv()
        );
    }

}
