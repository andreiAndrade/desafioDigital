package com.desafio.digital.votacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class VotacaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(VotacaoApplication.class, args);
	}

}

