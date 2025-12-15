package com.wirecard.challenge.paymentsapi.service;

import com.wirecard.challenge.paymentsapi.dto.BoletoRequest;
import com.wirecard.challenge.paymentsapi.dto.BoletoResponse;
import com.wirecard.challenge.paymentsapi.dto.PaymentRequest;
import com.wirecard.challenge.paymentsapi.dto.PaymentResponse;
import com.wirecard.challenge.paymentsapi.model.Payment;
import com.wirecard.challenge.paymentsapi.model.PaymentStatus;
import com.wirecard.challenge.paymentsapi.repository.PaymentRepository;

import java.time.LocalDate;

public class BoletoPaymentProcessor {

    private final BoletoService boletoService;
    private final PaymentRepository paymentRepository;
    public BoletoPaymentProcessor(
            BoletoService boletoService,
            PaymentRepository paymentRepository
    ) {
        this.boletoService = boletoService;
        this.paymentRepository = paymentRepository;
    }

    private PaymentResponse processBoleto(PaymentRequest paymentRequest) {
        BoletoResponse boletoResponse = criarBoleto();

        Payment payment = createPayment(paymentRequest, PaymentStatus.CREATED);
        Payment savedPayment = paymentRepository.save(payment);

        return PaymentResponse.builder()
                .id(savedPayment.getId())
                .status(savedPayment.getStatus())
                .message("Boleto gerado com sucesso")
                .boleto(boletoResponse)
                .build();
    }


    public BoletoResponse criarBoleto() {
        String code = "239398762934239398762934239398762934239398762934";
        LocalDate expirationDate = LocalDate.of(2008, 12, 30);
        BoletoRequest boletoRequest =
                new BoletoRequest(code, expirationDate);
        return boletoService.createBoleto(boletoRequest;
    }
}
