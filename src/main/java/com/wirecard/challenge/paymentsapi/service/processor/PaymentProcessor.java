package com.wirecard.challenge.paymentsapi.service.processor;

import com.wirecard.challenge.paymentsapi.dto.PaymentRequest;
import com.wirecard.challenge.paymentsapi.dto.PaymentResponse;
import com.wirecard.challenge.paymentsapi.model.PaymentMethod;

public interface PaymentProcessor {
    PaymentResponse process(PaymentRequest request);
    PaymentMethod getSupportedMethod();
}
