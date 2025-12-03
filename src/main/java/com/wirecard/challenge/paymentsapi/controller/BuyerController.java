package com.wirecard.challenge.paymentsapi.controller;

import java.util.List;

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
		return ResponseEntity.ok(list);
	}

	@GetMapping("/buyer/{id}")
	public ResponseEntity getBuyer(@PathVariable("id") Long id){
			List<Buyer> listaBuyer = br.findAll();
			Buyer buyerProcurado = null;
			
			for(Buyer buyer: listaBuyer){
				if(buyer.getId()==id) {
					buyerProcurado = buyer;
				}
			}
			
			if(buyerProcurado == null) {
				return new ResponseEntity("No Customer found for ID " + id, HttpStatus.BAD_GATEWAY);
			}
			return new ResponseEntity(buyerProcurado, HttpStatus.OK);
	}

	@PostMapping()
	public Buyer cadastrarBuyer(@RequestBody @Valid Buyer buyer) {
		return br.save(buyer);
	}

	@PostMapping()
	public ResponseEntity<BuyerResponse> cadastrarBuyer(@RequestBody @Valid BuyerRequest request) {
		BuyerResponse buyerResponse = buyerService.cadastrarBuyer(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(buyerResponse);
	}

	
	@DeleteMapping()
	public Buyer deletarBuyer(@RequestBody Buyer buyer) {
		br.delete(buyer);
		return buyer;
	}
}
