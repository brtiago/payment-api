package com.wirecard.challenge.paymentsapi.service;

import com.wirecard.challenge.paymentsapi.dto.*;
import com.wirecard.challenge.paymentsapi.model.Payment;
import com.wirecard.challenge.paymentsapi.repository.PaymentRepository;
import com.wirecard.challenge.paymentsapi.service.processor.PaymentProcessorFactory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final PaymentProcessorFactory processorFactory;

    public PaymentService(PaymentRepository paymentRepository,
                          PaymentMapper paymentMapper,
                          PaymentProcessorFactory processorFactory) {
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
        this.processorFactory = processorFactory;
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
                .orElseThrow(() -> new EntityNotFoundException(
                        "No Payment found for ID " + id
                ));
    }

    @Transactional
    public PaymentResponse cadastrarPayment(PaymentRequest request) {
        return processorFactory
                .getProcessor(request.paymentMethod())
                .process(request);
    };

    @Transactional
    public void deletarPayment(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Payment não encontrado: " + id
                ));
        paymentRepository.delete(payment);
    }


    // public boolean validarNumeroCartao(String cartaoNumero)
    // "Cartão inválido. Por favor, o código do Cartão deve possuir 16 carácteres numéricos (0-9) / ")

    // validarAmount(double amount) throws InvalidFormatException {
    // "Amount inválido, o valor máximo é R$1.000.000 / "

    // public boolean creditCardExists(CreditCard creditCard) {
    // "Negado. Cartão já cadastrado anteriormente."


}
