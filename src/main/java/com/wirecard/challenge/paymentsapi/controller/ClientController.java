package com.wirecard.challenge.paymentsapi.controller;

import jakarta.validation.Valid;

import com.wirecard.challenge.paymentsapi.dto.ClientResponse;
import com.wirecard.challenge.paymentsapi.model.Client;
import com.wirecard.challenge.paymentsapi.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


/** Classe responsável por controlar as operações e requisições referentes ao Cliente
 *
 *
 */

@RestController
@RequestMapping("/client")
public class ClientController {
	@Autowired
	private ClientRepository cr;

	@GetMapping(produces="application/json")
	public ResponseEntity<List<ClientResponse>> listaClients() {
        List<ClientResponse> lista = cr.findAll()
                .stream()
                .map(ClientResponse::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

	@PostMapping()
	@ResponseStatus(HttpStatus.OK)
	public Client cadastrarClient(@RequestBody @Valid Client client) {
		return cr.save(client);
	}

	@DeleteMapping()
	@ResponseStatus(HttpStatus.OK)
	public Client deletarClient(@RequestBody Client client) {
		cr.delete(client);
		return client;
	}
}
