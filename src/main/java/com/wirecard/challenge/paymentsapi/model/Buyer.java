package com.wirecard.challenge.paymentsapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

//Classe que representa o comprador//

@Entity

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Buyer {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String email;
	private String cpf;
	private String nome;

}
