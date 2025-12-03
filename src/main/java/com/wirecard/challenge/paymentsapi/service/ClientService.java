package com.wirecard.challenge.paymentsapi.service;

import com.wirecard.challenge.paymentsapi.dto.ClientResponse;
import com.wirecard.challenge.paymentsapi.repository.ClientRepository;

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
                .collect(Collectors.toList());
    }
}
