package com.wirecard.challenge.paymentsapi.model;

/* Classe que define e armazena o método de pagamento, assim como o Cartão de Crédito
ou o boleto */

public enum PaymentMethod {
	PIX,
    CREDIT_CARD,
    DEBIT_CARD,
	BOLETO,
    CASH;
}
