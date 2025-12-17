package com.wirecard.challenge.paymentsapi.repository;


import com.wirecard.challenge.paymentsapi.model.Payment;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @EntityGraph(attributePaths = {"buyer", "client"})
    Optional<Payment> findAllWithDetailsById(Long id);

    @EntityGraph(attributePaths = {"buyer", "client"})
    List<Payment> findAllWithDetails();

    @Query("SELECT p FROM Payment p " +
            "LEFT JOIN FETCH p.buyer " +
            "LEFT JOIN FETCH p.client " +
            "WHERE p.id = :id")
    Optional<Payment> findByIdWithDetails(@Param("id") Long id);

    @Override
    @EntityGraph(attributePaths = {"buyer", "client"})
    Optional<Payment> findById(Long id);

    @EntityGraph(attributePaths = {"buyer", "client"})
    List<Payment> findAll();
}
