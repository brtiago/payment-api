package com.wirecard.challenge.paymentsapi.controller;

import com.wirecard.challenge.paymentsapi.service.ClientService;
import jakarta.validation.Valid;

import com.wirecard.challenge.paymentsapi.dto.ClientRequest;
import com.wirecard.challenge.paymentsapi.dto.ClientResponse;
import com.wirecard.challenge.paymentsapi.model.Client;
import com.wirecard.challenge.paymentsapi.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/** Classe responsável por controlar as operações e requisições referentes ao Cliente
 *
 *
 */

@RestController
@RequestMapping("/client")
public class ClientController {
	@Autowired
	private ClientRepository cr;


	@Autowired
    private ClientService cs;

	@GetMapping(produces="application/json")
	public ResponseEntity<List<ClientResponse>> listaClients() {
        List<ClientResponse> lista = cs.listaClients();
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

	@PostMapping()
	public ResponseEntity<ClientResponse> cadastrarClient(@RequestBody @Valid ClientRequest request) {
		ClientResponse clientResponse = cs.cadastrarClient(request);
		return ResponseEntity.ok(clientResponse);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarClient(@PathVariable Long id) {
		cs.deletarClient(id);
		return ResponseEntity.noContent().build();
	}
}
