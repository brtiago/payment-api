package com.wirecard.challenge.paymentsapi.service;

import com.wirecard.challenge.paymentsapi.dto.ClientResponse;
import com.wirecard.challenge.paymentsapi.model.Client;
import com.wirecard.challenge.paymentsapi.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientRepository repository;

    @InjectMocks
    private ClientService service;

    @Test
    void buscarListaClient_QuandoNaoExistemClientes_DeveRetornarListaVazia() {
        // Given
        when(repository.findAll()).thenReturn(List.of());

        // When
        List<ClientResponse> responses = service.listaClients();

        // Then
        assertTrue(responses.isEmpty());
        verify(repository).findAll();
    }

    @Test
    void buscarListaClient_QuandoExistemClientes_DeveRetornarListaComTodosClientes() {
        // Given
        List<Client> clientes = List.of(
                Client.ClientBuilder.builder()
                        .withName("Amazon")
                        .build(),
                Client.ClientBuilder.builder()
                        .withName("Globo")
                        .build(),
                Client.ClientBuilder.builder()
                        .withName("Magalu")
                        .build()
        );
        when(repository.findAll()).thenReturn(clientes);

        // When
        List<ClientResponse> responses = service.listaClients();

        // Then
        int expectedSize = clientes.size();
        assertEquals(expectedSize, responses.size());
        assertAll(
                () -> assertEquals("Amazon", responses.get(0).name()),
                () -> assertEquals("Globo", responses.get(1).name()),
                () -> assertEquals("Magalu", responses.get(2).name())
        );
        verify(repository).findAll();
    }


}