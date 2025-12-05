package com.wirecard.challenge.paymentsapi.repository;

import com.wirecard.challenge.paymentsapi.model.Boleto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoletoRepository extends JpaRepository<Boleto, Long> {
}
