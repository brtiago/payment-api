package com.wirecard.challenge.paymentsapi.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record BoletoRequest(
        String code,
        @DateTimeFormat(pattern = "dd-MM-yyyy")
        LocalDate expirationDate,
        Double amount
) {
}
