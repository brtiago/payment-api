package com.wirecard.challenge.paymentsapi.controller;

import java.util.List;

import com.wirecard.challenge.paymentsapi.dto.BuyerRequest;
import com.wirecard.challenge.paymentsapi.service.BuyerService;
import jakarta.validation.Valid;

import com.wirecard.challenge.paymentsapi.dto.BuyerResponse;
import com.wirecard.challenge.paymentsapi.model.Buyer;
import com.wirecard.challenge.paymentsapi.repository.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**Classe responsável por controlar operações e requisições referentes ao Buyer
 *
 *
 */

@RestController
@RequestMapping("/buyer")
public class BuyerController {
	
	private final BuyerService buyerService;
	public BuyerController(BuyerService buyerService) {
		this.buyerService = buyerService;
	}
	
	@GetMapping
	public ResponseEntity<List<BuyerResponse>> listaBuyers() {
		List<BuyerResponse> lista = buyerService.listaBuyers();
		return ResponseEntity.ok(lista);
	}

	@GetMapping("/buyer/{id}")
	public ResponseEntity getBuyer(@PathVariable("id") Long id){
        BuyerResponse buyerFound = buyerService.getBuyer(id);
        return ResponseEntity.ok(buyerFound);
	}

    @PostMapping()
    public ResponseEntity<BuyerResponse> cadastrarBuyer(@RequestBody @Valid BuyerRequest request) {
        BuyerResponse buyerResponse = buyerService.cadastrarBuyer(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(buyerResponse);
    }

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarBuyer(@PathVariable Long id) {
		buyerService.deletarBuyer(id);
		return ResponseEntity.noContent().build();
	}
}
