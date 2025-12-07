package com.wirecard.challenge.paymentsapi.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
	
	@NotNull
	@OneToOne(cascade = {CascadeType.ALL})
	private PaymentMethod paymentMethod;
	
	//Define o Status do pagamento//
	private PaymentStatus status;
	
	@ManyToOne
	private Buyer buyer;
	
	@ManyToOne
	private Client client;
	
}
