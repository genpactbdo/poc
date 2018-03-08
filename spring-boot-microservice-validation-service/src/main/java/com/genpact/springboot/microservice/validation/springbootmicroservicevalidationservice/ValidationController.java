package com.genpact.springboot.microservice.validation.springbootmicroservicevalidationservice;

import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValidationController {
  
  @GetMapping("/validation")
  public boolean isValid(){
    
	  Random r = new Random();
	  return r.nextBoolean();
	  
  }
}