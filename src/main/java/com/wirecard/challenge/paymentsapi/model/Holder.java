package com.wirecard.challenge.paymentsapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

//Classe que representa o proprietário do Cartão de Crédito//
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Holder {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String name;
	
	// Data de Nascimento //
	
	private String birthDate;
	
	//Número do documento //
	
	private String documentNumber;

}
