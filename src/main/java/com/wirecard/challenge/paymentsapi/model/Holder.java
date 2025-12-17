package com.wirecard.challenge.paymentsapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

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

    @Column(nullable = false)
	private String name;
	
	// Data de Nascimento //
    @Column(nullable = false)
	private LocalDate birthDate;
	
	//Número do documento //
    @Column(nullable = false)
	private String documentNumber;

}
