package com.wirecard.challenge.paymentsapi.controller;

import com.wirecard.challenge.paymentsapi.model.PaymentMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/payment-methods")
public class PaymentMethodController {

    @GetMapping
    public ResponseEntity<List<Map<String, String>>> listarMetodosPagamento() {
        List<Map<String, String>> methods = Arrays.stream(PaymentMethod.values())
                .map(method -> Map.of(
                        "name", method.name(),
                        "description", method.getDescription()
                )).collect(Collectors.toList());

        return ResponseEntity.ok(methods);
    }
}
