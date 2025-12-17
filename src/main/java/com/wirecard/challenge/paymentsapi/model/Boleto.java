package com.wirecard.challenge.paymentsapi.model;

import com.wirecard.challenge.paymentsapi.dto.BoletoRequest;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


// Classe que representa a forma de pagamento "BOLETO"//
@Entity
@Table(name = "boletos")

@Builder
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Boleto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

	//Código de pagamento do boleto
    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private Double amount;

	//Data de vencimento do boleto.
    @Column(nullable = false)
	private LocalDate expirationDate;
}
