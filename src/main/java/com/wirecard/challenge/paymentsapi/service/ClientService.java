package com.wirecard.challenge.paymentsapi.service;

import com.wirecard.challenge.paymentsapi.model.Client;
import com.wirecard.challenge.paymentsapi.dto.ClientRequest;
import com.wirecard.challenge.paymentsapi.dto.ClientResponse;
import com.wirecard.challenge.paymentsapi.repository.ClientRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<ClientResponse> listaClients() {
        return clientRepository.findAll()
                .stream()
                .map(ClientResponse::fromEntity)
                .toList();
    }

    @Transactional
    public ClientResponse cadastrarClient(ClientRequest request) {
        Client entidade = new Client(request);
        Client salvo = clientRepository.save(entidade);
        return ClientResponse.fromEntity(salvo);
    }

    @Transactional
    public void deletarClient(Long id) {
        Client client = clientRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
        clientRepository.delete(client);
    }
}
