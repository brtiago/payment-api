package com.wirecard.challenge.paymentsapi.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

/* Classe que representa o Cartão de Crédito utilizado nos pagamentos,
pelos compradores */

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreditCard {

	@Id 
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	//Bandeira do cartão, ex: Visa, MasterCard, etc..//

    private String brand;
	
	//Proprietário do cartão de crédito (nome impresso) //

    @OneToOne(cascade = {CascadeType.ALL})
	private Holder holder;
	
	//Número do cartão de crédito//
	
	@NotBlank(message="Favor preencher o número do cartão. ")
	private String cardNumber;
	
	//Data de validade do cartão de crédito//
	
	private LocalDate expirationDate;
	
	//Código verificador do cartão//
	
	@NotBlank(message="Favor preencher o CVV")
	private String cvv;

    public boolean validarCC(String cardNumber) {
        return cardNumber.matches("\\d{16}");
    }

    public boolean validarDV(String dv) {
        return dv.matches("\\d{3}");
    }

}
