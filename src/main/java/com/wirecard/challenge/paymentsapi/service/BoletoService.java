package com.wirecard.challenge.paymentsapi.service;

import com.wirecard.challenge.paymentsapi.dto.BoletoMapper;
import com.wirecard.challenge.paymentsapi.dto.BoletoRequest;
import com.wirecard.challenge.paymentsapi.dto.BoletoResponse;
import com.wirecard.challenge.paymentsapi.model.Boleto;
import com.wirecard.challenge.paymentsapi.repository.BoletoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class BoletoService {

    private final BoletoRepository boletoRepository;
    private final BoletoMapper boletoMapper;

    public BoletoService(BoletoRepository boletoRepository, BoletoMapper boletoMapper) {
        this.boletoRepository = boletoRepository;
        this.boletoMapper = boletoMapper;
    }

    @Transactional
    public BoletoResponse createBoleto(BoletoRequest request) {
        Boleto entidade = boletoMapper.toEntity(request);
        Boleto salvo = boletoRepository.save(entidade);
        return boletoMapper.toResponse(salvo);
    }
}
