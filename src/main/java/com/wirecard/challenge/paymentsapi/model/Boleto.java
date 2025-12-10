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
    private String code;

	//Data de vencimento do boleto.
	private LocalDate expirationDate;

    public Boleto(BoletoRequest request) {
        this.code = request.code();
        this.expirationDate = request.expirationDate();
    }

    public Boleto(String code, LocalDate expirationDate) {
        this.code = code;
        this.expirationDate = expirationDate;
    }
}
