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

	public Client() {}

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


    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private String name;

        private Builder() {}

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Client build() {
            Client client = new Client();
            if (this.id != null) {
                client.setId(this.id);
            }
            if (this.name != null) {
                client.setName(this.name);
            }
            return client;
        }

        public static Builder builder() {
            return Builder.builder();
        }

    }

}


