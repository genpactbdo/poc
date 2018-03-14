package com.genpact.springboot.microservice.fileimport.springbootmicroservicefileimport.springbootmicroservicefileimportservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.genpact.springboot.microservice.fileimport.springbootmicroservicefileimport.springbootmicroservicefileimportservice"})
public class SpringBootMicroserviceFileimportServiceApplication {
	
	public static void main(String[] args) {		
		SpringApplication.run(SpringBootMicroserviceFileimportServiceApplication.class, args);
	
	}
	
}
