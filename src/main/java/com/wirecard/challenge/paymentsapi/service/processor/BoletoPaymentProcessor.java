package com.wirecard.challenge.paymentsapi.service.processor;

import com.wirecard.challenge.paymentsapi.dto.*;
import com.wirecard.challenge.paymentsapi.model.Payment;
import com.wirecard.challenge.paymentsapi.model.PaymentMethod;
import com.wirecard.challenge.paymentsapi.model.PaymentStatus;
import com.wirecard.challenge.paymentsapi.repository.PaymentRepository;
import com.wirecard.challenge.paymentsapi.service.BoletoService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class BoletoPaymentProcessor implements PaymentProcessor {
    private final BoletoService boletoService;
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    public BoletoPaymentProcessor(
            BoletoService boletoService,
            PaymentRepository paymentRepository,
            PaymentMapper paymentMapper
    ) {
        this.boletoService = boletoService;
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
    }

    @Override
    @Transactional
    public PaymentResponse process(PaymentRequest request) {
        gerarBoleto(request.amount());

        Payment payment = Payment.builder()
                .amount(request.amount())
                .paymentMethod(PaymentMethod.BOLETO)
                .status(PaymentStatus.CREATED)
                .buyer(request.buyer())
                .client(request.client())
                .build();

        Payment savedPayment = paymentRepository.save(payment);

        Payment paymentWithDetails = paymentRepository
                .findByIdWithDetails(savedPayment.getId())
                .orElseThrow(() -> new RuntimeException("Erro ao buscar pagamento"));

        return paymentMapper.toResponse(paymentWithDetails);
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
        String code = "239398762934239398762934239398762934239398762934";
        return code;
    }
}
