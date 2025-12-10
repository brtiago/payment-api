package com.wirecard.challenge.paymentsapi.model;

import com.wirecard.challenge.paymentsapi.dto.PaymentRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

//Classe que representa o pagamento realizado //

@Entity
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//Quantia R$ do pagamento//
	
	@NotNull
	private Double amount;
	
	//Define e armazena o método de pagamento//
	
	@Enumerated(EnumType.STRING)
	private PaymentMethod paymentMethod;
	
	//Define o Status do pagamento//
    @Enumerated(EnumType.STRING)
	private PaymentStatus status;
	
	@ManyToOne
	private Buyer buyer;
	
	@ManyToOne
	private Client client;

    public Payment(PaymentRequest request) {
        this.amount = request.amount();
        this.paymentMethod = request.paymentMethod();
        setStatus(PaymentStatus.CREATED);
        this.buyer = request.buyer();
        this.client = request.client();
    }

}
