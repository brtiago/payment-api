package com.wirecard.challenge.paymentsapi.service;

import com.wirecard.challenge.paymentsapi.dto.BuyerRequest;
import com.wirecard.challenge.paymentsapi.dto.BuyerResponse;
import com.wirecard.challenge.paymentsapi.model.Buyer;
import com.wirecard.challenge.paymentsapi.repository.BuyerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuyerService {

    private final BuyerRepository buyerRepository;
    public BuyerService(BuyerRepository buyerRepository) {
        this.buyerRepository = buyerRepository;
    }

    public List<BuyerResponse> listaBuyers() {
        return buyerRepository.findAll()
                .stream()
                .map(BuyerResponse::fromEntity)
                .toList();
    }

    @Transactional
    public BuyerResponse cadastrarBuyer(BuyerRequest request) {
        Buyer entidade = new Buyer(request);
        Buyer salvo = buyerRepository.save(entidade);
        return BuyerResponse.fromEntity(salvo);
    }

}
