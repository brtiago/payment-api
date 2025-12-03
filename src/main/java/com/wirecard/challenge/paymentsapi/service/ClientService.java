package com.wirecard.challenge.paymentsapi.service;

import com.wirecard.challenge.paymentsapi.model.Client;
import com.wirecard.challenge.paymentsapi.dto.ClientRequest;
import com.wirecard.challenge.paymentsapi.dto.ClientResponse;
import com.wirecard.challenge.paymentsapi.repository.ClientRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientRepository cr;
    public ClientService(ClientRepository cr) {
        this.cr = cr;
    }

    public List<ClientResponse> listaClients() {
        return cr.findAll()
                .stream()
                .map(ClientResponse::fromEntity)
                .toList();
    }

    @Transactional
    public ClientResponse cadastrarClient(ClientRequest request) {
        Client entidade = new Client(request);
        Client salvo = cr.save(entidade);
        return ClientResponse.fromEntity(salvo);
    }

    @Transactional
    public void deletarClient(Long id) {
        Client client = cr.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
        cr.delete(client);
    }
}
