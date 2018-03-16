package com.genpact.springboot.microservice.fileimport.springbootmicroservicefileimport.springbootmicroservicefileimportservice;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.genpact.springboot.microservice.fileimport.springbootmicroservicefileimport.springbootmicroservicefileimportservice.model.Remittance;

@FeignClient(name="authorization-service")
@RibbonClient(name="forex-service")
public interface AuthorizeServiceProxy {
  @PostMapping("/authorize")
  public void authorize(Remittance remittance);
}