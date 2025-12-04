package com.wirecard.challenge.paymentsapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import com.wirecard.challenge.paymentsapi.dto.ClientRequest;

//Classe que representa o Cliente que receberá o pagamento//

@Entity
public class Client {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="client_id")
	private Long id;
	
	@Column(name="name")
	private String name;

	public Client() {
	}

    public record ClientBuilder(String name) {
        public Client build() {
            return new Client(new ClientRequest(this.name));
        }

        public static ClientBuilder builder() {
            return new ClientBuilder(null);
        }

        public ClientBuilder withName(String name) {
            return new ClientBuilder(name);
        }
    }

	public Client(ClientRequest request) {
		this.name = request.name();
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + "]";
	}

}


