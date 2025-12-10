package com.wirecard.challenge.paymentsapi.service;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.wirecard.challenge.paymentsapi.dto.*;
import com.wirecard.challenge.paymentsapi.model.CreditCard;
import com.wirecard.challenge.paymentsapi.model.Payment;
import com.wirecard.challenge.paymentsapi.model.PaymentStatus;
import com.wirecard.challenge.paymentsapi.repository.PaymentRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
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

    @Transactional
    public PaymentResponse cadastrarPayment(PaymentRequest request) {
        return switch (request.paymentMethod()) {
            case BOLETO -> processBoletoPayment(request);
            case CREDIT_CARD, DEBIT_CARD -> processCardPayment(request);
            case PIX -> processPixPayment(request);
            case CASH -> processCashPayment(request);
        };
    }

    public String cadastrarPayment(@RequestBody @Valid Payment payment) throws Exception {

        this.stringBuilder.delete(0, stringBuilder.length());

        //Se o pagamento for boleto, o método cria um código e uma data e retorna para o usuário.

        if(payment.getPaymentMethod().getMethod().name().equals("BOLETO")) {
            payment.getPaymentMethod().setBoleto(this.criarBoleto());
            payment.setStatus(PaymentStatus.CREATED);
            try {
                stringBuilder.append("/ Pagamento cadastrado com sucesso!! //");
                return this.stringBuilder + "\nCódigo do boleto: " +
                        paymentRepository.save(payment).getPaymentMethod().getBoleto().getCode() +
                        " \nData de Vencimento: " +
                        payment.getPaymentMethod().getBoleto().getExpirationDate();
            }
            catch(Exception e) {
                return "Erro";
            }
        }

        /**
         * Verificar se: 1) Cartão já foi cadastrado Anteriormente 2)Amount é valido,
         * 3) Número do Cartão é valido e 4) CVV é válido
         */

        else if(!(creditCardExists(payment.getPaymentMethod().getCreditCard())) &&
                validarAmount(payment.getAmount()) &&
                validarCartao(payment.getPaymentMethod().getCreditCard().getCardNumber(),
                        payment.getPaymentMethod().getCreditCard().getCvv())) {

            payment.setStatus(PaymentStatus.CREATED);
            stringBuilder.append("/ Pagamento cadastrado com sucesso!! //");
            return this.stringBuilder + " Status atual: " + paymentRepository.save(payment).getStatus().name();
        }
        return "Erros encontrados: " + this.stringBuilder;

    }

    private PaymentResponse processBoletoPayment(PaymentRequest paymentRequest) {
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

    public BoletoResponse criarBoleto() {
        String code = "239398762934239398762934239398762934239398762934";
        LocalDate expirationDate = LocalDate.of(2008, 12, 30);
        BoletoRequest boletoRequest =
                new BoletoRequest(code, expirationDate);
        return boletoService.createBoleto(boletoRequest);
    }

    public boolean validarNumeroCartao(String cartaoNumero) {
        if(ccc.validarNumeroCartao(cartaoNumero)) {
            return true;
        }
        stringBuilder.append(" /Cartão inválido. Por favor, o código do Cartão deve possuir 16 carácteres numéricos (0-9) / ");
        return false;
    }



    public boolean validarCartao(String cartaoNumero, String dv) {
        return(validarNumeroCartao(cartaoNumero) && validarDV(dv));
    }


    public boolean validarAmount(double amount) throws InvalidFormatException {
        if(amount < 1000000.00){
            return true;

        }
        stringBuilder.append(("/ Amount inválido, o valor máximo é R$1.000.000 / "));
        return false;
    }


    public boolean creditCardExists(CreditCard creditCard) {
        if(ccc.creditCardExists(creditCard)) {
            stringBuilder.append(("Negado. Cartão já cadastrado anteriormente."));
            return true;
        }
        return false;
    }

}
