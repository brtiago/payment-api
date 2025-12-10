package com.wirecard.challenge.paymentsapi.service;

import com.wirecard.challenge.paymentsapi.dto.ClientMapper;
import com.wirecard.challenge.paymentsapi.model.Client;
import com.wirecard.challenge.paymentsapi.dto.ClientRequest;
import com.wirecard.challenge.paymentsapi.dto.ClientResponse;
import com.wirecard.challenge.paymentsapi.repository.ClientRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static java.util.stream.Collectors.toList;

@Service
@Validated
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    public List<ClientResponse> listaClients() {
        return clientRepository.findAll()
                .stream()
                .map(clientMapper::toResponse)
                .toList();
    }

    @Transactional
    public ClientResponse cadastrarClient(@Valid ClientRequest request) {
        Client entidade = clientMapper.toEntity(request);
        Client salvo = clientRepository.save(entidade);
        return clientMapper.toResponse(salvo);
    }

    @Transactional
    public void deletarClient(Long id) {
        Client client = clientRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
        clientRepository.delete(client);
    }
}
