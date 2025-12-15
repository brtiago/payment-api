package com.wirecard.challenge.paymentsapi.service;

import com.wirecard.challenge.paymentsapi.dto.*;
import com.wirecard.challenge.paymentsapi.model.Payment;
import com.wirecard.challenge.paymentsapi.repository.PaymentRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final BoletoService boletoService;
    private final PaymentMapper paymentMapper;

    public PaymentService(PaymentRepository paymentRepository,
                          PaymentMapper paymentMapper,
                          BoletoService boletoService) {
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
        this.boletoService = boletoService;
    }

    public List<PaymentResponse> listaPayments() {
        return paymentRepository.findAll()
                .stream()
                .map(paymentMapper::toResponse)
                .toList();
    }

    public PaymentResponse getPayment(Long id) {
        return paymentRepository.findById(id)
                .map(paymentMapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("No Payment found for ID \" + id"));
    }

    public PaymentResponse cadastrarPayment(PaymentRequest request) {
        return switch (request.paymentMethod()) {
            case BOLETO -> processBoletoPayment(request);
            case CREDIT_CARD, DEBIT_CARD -> processCardPayment(request);
            case PIX -> processPixPayment(request);
            case CASH -> processCashPayment(request);
        };
    }

    private PaymentResponse processCardPayment(PaymentRequest request) {
    }

    private PaymentResponse processPixPayment(PaymentRequest request) {
    }

    private PaymentResponse processCashPayment(PaymentRequest request) {
    }


    @Transactional
    public void deletarPayment(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Payment não encontrado"));
        paymentRepository.delete(payment);
    }


    // public boolean validarNumeroCartao(String cartaoNumero)
    // "Cartão inválido. Por favor, o código do Cartão deve possuir 16 carácteres numéricos (0-9) / ")

    // validarAmount(double amount) throws InvalidFormatException {
    // "Amount inválido, o valor máximo é R$1.000.000 / "

    // public boolean creditCardExists(CreditCard creditCard) {
    // "Negado. Cartão já cadastrado anteriormente."


}
