package com.genpact.springboot.microservice.remittance.springbootmicroserviceremittanceservice;

import java.math.BigDecimal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RemittanceController {

  //private Logger logger = LoggerFactory.getLogger(this.getClass());

  @GetMapping("/remit/from/{from}/to/{to}/quantity/{quantity}")
  public RemittanceBean convertCurrency(@PathVariable String from, @PathVariable String to,
      @PathVariable BigDecimal quantity) {

	  
	  RemittanceBean remittance = new RemittanceBean();
    /*Map<String, String> uriVariables = new HashMap<>();
    uriVariables.put("from", from);
    uriVariables.put("to", to);*/

    ResponseEntity<Boolean> responseEntity = new RestTemplate().getForEntity(
        "http://localhost:8200/feedback", Boolean.class);

    // File processing = ok
    
    // Validation - random true or false
    
    Boolean response = responseEntity.getBody();
    remittance.setValid(response.booleanValue());
    
    // authorization - automatic or manual
    // if(automatic) else {}
    
    // segregation (DTD or CTA)
    
    // Delivery (courier depends on product)
    
    // Feedback - create files like 2018MAR13_ML, 2018MAR13_SB

    return remittance;
  }
  
}