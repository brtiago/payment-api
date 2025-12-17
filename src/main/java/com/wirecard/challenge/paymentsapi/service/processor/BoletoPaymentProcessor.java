package com.wirecard.challenge.paymentsapi.service.processor;

import com.wirecard.challenge.paymentsapi.dto.*;
import com.wirecard.challenge.paymentsapi.model.*;
import com.wirecard.challenge.paymentsapi.repository.BuyerRepository;
import com.wirecard.challenge.paymentsapi.repository.ClientRepository;
import com.wirecard.challenge.paymentsapi.repository.PaymentRepository;
import com.wirecard.challenge.paymentsapi.service.BoletoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class BoletoPaymentProcessor implements PaymentProcessor {
    private final BoletoService boletoService;
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final BuyerRepository buyerRepository;
    private final ClientRepository clientRepository;

    public BoletoPaymentProcessor(
            BoletoService boletoService,
            PaymentRepository paymentRepository,
            PaymentMapper paymentMapper,
            BuyerRepository buyerRepository,
            ClientRepository clientRepository
    ) {
        this.boletoService = boletoService;
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
        this.buyerRepository = buyerRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    @Transactional
    public PaymentResponse process(PaymentRequest request) {
        gerarBoleto(request.amount());

        Buyer buyer = buyerRepository.findById(request.buyer().getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Buyer não encontrado com ID: " + request.buyer().getId()
                        ));
        Client client = clientRepository.findById(request.buyer().getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Client não encontrado com ID: " + request.client().getId()
                ));

        Payment payment = Payment.builder()
                .amount(request.amount())
                .paymentMethod(PaymentMethod.BOLETO)
                .status(PaymentStatus.CREATED)
                .buyer(buyer)
                .client(client)
                .build();

        Payment savedPayment = paymentRepository.save(payment);

        return paymentMapper.toResponse(savedPayment);
    }

    @Override
    public PaymentMethod getSupportedMethod() {
        return PaymentMethod.BOLETO;
    }

    private BoletoResponse gerarBoleto(Double amount) {
        String code = gerarCodigoBarras();
        LocalDate expirationDate = calcularDataVencimento();

        BoletoRequest boletoRequest = new BoletoRequest(code, expirationDate, amount);
        return boletoService.createBoleto(boletoRequest);
    }

    private LocalDate calcularDataVencimento() {
        return LocalDate.now().plusDays(3);
    }

    private String gerarCodigoBarras() {
        return "239398762934239398762934239398762934239398762934";
    }
}
