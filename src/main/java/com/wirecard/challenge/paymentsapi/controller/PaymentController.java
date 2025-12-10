package com.wirecard.challenge.paymentsapi.controller;

import java.util.List;

import com.wirecard.challenge.paymentsapi.dto.PaymentRequest;
import com.wirecard.challenge.paymentsapi.dto.PaymentResponse;
import com.wirecard.challenge.paymentsapi.service.PaymentService;
import jakarta.validation.Valid;

import com.wirecard.challenge.paymentsapi.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/** Classe responsável por gerenciar as operações e requisições referentes aos Pagamentos
 *
 *
 */

@RestController
@RequestMapping("/payment")
public class PaymentController {

	String resposta = "";
	
	//Construção da String de resposta para o usuário
	
	StringBuilder stringBuilder = new StringBuilder(resposta);


    private final PaymentRepository paymentRepository;
    private final PaymentService paymentService;
    public PaymentController(
            PaymentRepository paymentRepository,
            PaymentService paymentService
    ) {
        this.paymentRepository = paymentRepository;
        this.paymentService = paymentService;
    }

	@Autowired
	private CreditCardController ccc;
	
	@Autowired
	private BoletoController bc;


    @GetMapping()
    public ResponseEntity<List<PaymentResponse>> listaPayments() {
        List<PaymentResponse> lista = paymentService.listaPayments();
        return ResponseEntity.ok(lista);
    }

	@GetMapping("/{id}")
	public ResponseEntity getPayment(@PathVariable Long id){
        PaymentResponse paymentFound = paymentService.getPayment(id);
        return ResponseEntity.ok(paymentFound);
	}


    @PostMapping()
    public ResponseEntity<PaymentResponse> cadastrarPayment(@RequestBody @Valid PaymentRequest request) {
        PaymentResponse paymentCriado = paymentService.cadastrarPayment(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentCriado);
    }


    @DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarPayment(@PathVariable Long id) {
        paymentService.deletarPayment(id);
        return ResponseEntity.noContent().build();
    }

}