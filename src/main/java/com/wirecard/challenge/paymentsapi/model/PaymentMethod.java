package com.wirecard.challenge.paymentsapi.model;

public enum PaymentMethod {
    PIX("Pix"),
    CREDIT_CARD("Cartão de Crédito"),
    DEBIT_CARD("Cartão de Débito"),
    BOLETO("Boleto Bancário"),
    CASH("Dinheiro");

    private final String description;

    PaymentMethod(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
