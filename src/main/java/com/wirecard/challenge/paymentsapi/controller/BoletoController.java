package com.wirecard.challenge.paymentsapi.controller;

import com.wirecard.challenge.paymentsapi.dto.BoletoRequest;
import com.wirecard.challenge.paymentsapi.dto.BoletoResponse;
import com.wirecard.challenge.paymentsapi.service.BoletoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Classe responsável por controlar operações e requisições referentes aos Boletos
 *
 *
 */

@RestController
@RequestMapping("/boleto")
public class BoletoController {

    private final BoletoService boletoService;
    public BoletoController(BoletoService boletoService) {
        this.boletoService = boletoService;
    }

    public ResponseEntity<BoletoResponse> createBoleto(@RequestBody BoletoRequest request) {
        BoletoResponse boletoCriado = boletoService.createBoleto(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(boletoCriado);
    }
}
