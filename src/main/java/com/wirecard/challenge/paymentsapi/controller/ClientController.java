package com.wirecard.challenge.paymentsapi.controller;

import com.wirecard.challenge.paymentsapi.service.ClientService;
import jakarta.validation.Valid;

import com.wirecard.challenge.paymentsapi.dto.ClientRequest;
import com.wirecard.challenge.paymentsapi.dto.ClientResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/** Classe responsável por controlar as operações e requisições referentes ao Cliente
 *
 *
 */

@RestController
@RequestMapping("/client")
public class ClientController {

	private final ClientService clientService;
	public ClientController(ClientService clientService) {
		this.clientService = clientService;
	}

	@GetMapping()
	public ResponseEntity<List<ClientResponse>> listaClients() {
        List<ClientResponse> lista = clientService.listaClients();
        return ResponseEntity.ok(lista);
    }

	@PostMapping()
	public ResponseEntity<ClientResponse> cadastrarClient(@RequestBody @Valid ClientRequest request) {
		ClientResponse clientResponse = clientService.cadastrarClient(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(clientResponse);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarClient(@PathVariable Long id) {
		clientService.deletarClient(id);
		return ResponseEntity.noContent().build();
	}
}
