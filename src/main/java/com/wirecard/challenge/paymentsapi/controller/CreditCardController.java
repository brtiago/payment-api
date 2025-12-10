package com.wirecard.challenge.paymentsapi.controller;

import java.util.List;

import com.wirecard.challenge.paymentsapi.dto.CreditCardRequest;
import com.wirecard.challenge.paymentsapi.dto.CreditCardResponse;
import com.wirecard.challenge.paymentsapi.service.CreditCardService;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/** Classe responsável por controlar operações e requisições referentes aos Cartões
 *
 *
 */

@RestController
@RequestMapping("/creditcard")
public class CreditCardController {

    private final CreditCardService cardService;
    public CreditCardController(CreditCardService cardService) {
        this.cardService = cardService;
    }

	@GetMapping()
	public ResponseEntity<List<CreditCardResponse>> listaCreditCards() {
		List<CreditCardResponse> listaCC = cardService.listaCreditCards();
		return ResponseEntity.ok(listaCC);
	}

	@PostMapping()
	public ResponseEntity<CreditCardResponse> cadastrarCreditCard(
            @RequestBody @Valid CreditCardRequest creditCardRequest)
    {
        CreditCardResponse response =
                cardService.cadastrarCreditCard(creditCardRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarCreditCard(@PathVariable Long id) {
		cardService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
