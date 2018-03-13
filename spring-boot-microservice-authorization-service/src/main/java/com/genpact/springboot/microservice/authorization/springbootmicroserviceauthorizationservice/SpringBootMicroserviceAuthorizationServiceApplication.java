package com.genpact.springboot.microservice.authorization.springbootmicroserviceauthorizationservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.genpact.springboot.microservice.authorization.springbootmicroserviceauthorizationservice.model.Remittance;
import com.genpact.springboot.microservice.authorization.springbootmicroserviceauthorizationservice.repository.RemittanceRepository;

@SpringBootApplication
public class SpringBootMicroserviceAuthorizationServiceApplication {

	private static final Logger log = LoggerFactory.getLogger(SpringBootMicroserviceAuthorizationServiceApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootMicroserviceAuthorizationServiceApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(RemittanceRepository repository) {
		return (args) -> {
			
			//repository.save(new Remittance("ML"));
			
			for (Remittance customer : repository.findAll()) {
				log.info(customer.getConduit());
			}
			log.info(repository.toString());

		};
	}
}
