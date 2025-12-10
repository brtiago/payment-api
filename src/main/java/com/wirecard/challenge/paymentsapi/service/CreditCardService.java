package com.wirecard.challenge.paymentsapi.service;

import com.wirecard.challenge.paymentsapi.dto.CreditCardMapper;
import com.wirecard.challenge.paymentsapi.dto.CreditCardRequest;
import com.wirecard.challenge.paymentsapi.dto.CreditCardResponse;
import com.wirecard.challenge.paymentsapi.model.CreditCard;
import com.wirecard.challenge.paymentsapi.repository.CreditCardRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditCardService {

    private final CreditCardRepository creditCardRepository;
    private final CreditCardMapper creditCardMapper;
    public CreditCardService(
            CreditCardRepository creditCardRepository,
            CreditCardMapper creditCardMapper
    ) {
        this.creditCardRepository = creditCardRepository;
        this.creditCardMapper = creditCardMapper;
    }

    public List<CreditCardResponse> listaCreditCards() {
        return creditCardRepository.findAll()
                .stream()
                .map(creditCardMapper::toResponse)
                .toList();
    }

    public CreditCardResponse getCreditCard(String cardNumber) {
        return creditCardRepository.findByCardNumber(cardNumber)
                .map(creditCardMapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Credit card not found: " + cardNumber));
    }

    public CreditCardResponse cadastrarCreditCard(CreditCardRequest request) {
        CreditCard entidade = creditCardMapper.toEntity(request);
        CreditCard salvo = creditCardRepository.save(entidade);
        return creditCardMapper.toResponse(salvo);
    }

    @Transactional
    public void delete(Long id) {
        CreditCard creditCard = creditCardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Credit Card não encontrado"));
        creditCardRepository.delete(creditCard);
    }

}
