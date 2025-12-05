package com.wirecard.challenge.paymentsapi.service;

import com.wirecard.challenge.paymentsapi.dto.BoletoRequest;
import com.wirecard.challenge.paymentsapi.dto.BoletoResponse;
import com.wirecard.challenge.paymentsapi.model.Boleto;
import com.wirecard.challenge.paymentsapi.repository.BoletoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class BoletoService {

    private final BoletoRepository boletoRepository;

    public BoletoService(BoletoRepository boletoRepository) {
        this.boletoRepository = boletoRepository;
    }

    @Transactional
    public BoletoResponse createBoleto(BoletoRequest request) {
        Boleto entidade = new Boleto(request);
        Boleto salvo = boletoRepository.save(entidade);
        return BoletoResponse.fromEntity(salvo);
    }
}
