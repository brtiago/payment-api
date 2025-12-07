package com.wirecard.challenge.paymentsapi.service;

import com.wirecard.challenge.paymentsapi.dto.BuyerMapper;
import com.wirecard.challenge.paymentsapi.dto.BuyerRequest;
import com.wirecard.challenge.paymentsapi.dto.BuyerResponse;
import com.wirecard.challenge.paymentsapi.model.Buyer;
import com.wirecard.challenge.paymentsapi.repository.BuyerRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuyerService {

    private final BuyerRepository buyerRepository;
    private final BuyerMapper buyerMapper;

    public BuyerService(BuyerRepository buyerRepository, BuyerMapper buyerMapper) {
        this.buyerRepository = buyerRepository;
        this.buyerMapper = buyerMapper;
    }

    public List<BuyerResponse> listaBuyers() {
        return buyerRepository.findAll()
                .stream()
                .map(buyerMapper::toResponse)
                .toList();
    }

    public BuyerResponse getBuyer(Long id) {
        return buyerRepository.findById(id)
                .map(buyerMapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("No Customer found for ID " + id));
    }

    @Transactional
    public BuyerResponse cadastrarBuyer(BuyerRequest request) {
        Buyer entidade = new Buyer(request);
        Buyer salvo = buyerRepository.save(entidade);
        return buyerMapper.toResponse(salvo);
    }

    @Transactional
    public void deletarBuyer(Long id) {
        Buyer buyer = buyerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Buyer não encontrado"));
        buyerRepository.delete(buyer);
    }

}
