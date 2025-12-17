package com.wirecard.challenge.paymentsapi.service.processor;

import com.wirecard.challenge.paymentsapi.dto.PaymentMapper;
import com.wirecard.challenge.paymentsapi.dto.PaymentRequest;
import com.wirecard.challenge.paymentsapi.dto.PaymentResponse;
import com.wirecard.challenge.paymentsapi.exception.PaymentValidationException;
import com.wirecard.challenge.paymentsapi.model.*;
import com.wirecard.challenge.paymentsapi.repository.BuyerRepository;
import com.wirecard.challenge.paymentsapi.repository.ClientRepository;
import com.wirecard.challenge.paymentsapi.repository.PaymentRepository;
import com.wirecard.challenge.paymentsapi.service.CreditCardService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class CardPaymentProcessor implements PaymentProcessor {

    private final CreditCardService creditCardService;
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final BuyerRepository buyerRepository;
    private final ClientRepository clientRepository;

    public CardPaymentProcessor(
            CreditCardService creditCardService,
            PaymentRepository paymentRepository,
            PaymentMapper paymentMapper,
            BuyerRepository buyerRepository,
            ClientRepository clientRepository
    ) {
        this.creditCardService = creditCardService;
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
        this.buyerRepository = buyerRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    @Transactional
    public PaymentResponse process(PaymentRequest request) {
        validarCartao(request);

        Buyer buyer = buyerRepository.findById(request.buyer().getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Buyer não encontrado com ID: " + request.buyer().getId()
                ));

        Client client = clientRepository.findById(request.client().getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Client não encontrado com ID: " + request.client().getId()
                ));

        Payment payment = Payment.builder()
                .amount(request.amount())
                .paymentMethod(request.paymentMethod())
                .status(PaymentStatus.IN_ANALYSIS)
                .buyer(buyer)
                .client(client)
                .build();

        boolean autorizado = simularAutorizado(request);

        if(autorizado) {
            payment.setStatus(PaymentStatus.AUTHORIZED);
        } else {
            payment.setStatus(PaymentStatus.CANCELLED);
        }

        Payment savedPayment = paymentRepository.save(payment);
        return paymentMapper.toResponse(savedPayment);
    }

    @Override
    public PaymentMethod getSupportedMethod() {
        return PaymentMethod.CREDIT_CARD;
    }

    private void validarCartao(PaymentRequest request) throws PaymentValidationException {
        if(request.amount() == null || request.amount() <= 0) {
            throw new PaymentValidationException("Valor inválido");
        }

        if(request.amount() > 1_000_000) {
            throw new PaymentValidationException(
                    "Valor máximo excedido. Limite: R$ 1.000.000,00"
            );
        }
    }

    private boolean simularAutorizado(PaymentRequest request) {
        // Quando em produção: integrar com gateway de pagamento
        // Por enquanto, simular 95% de aprovação
        return Math.random() > 0.05;
    }
}
