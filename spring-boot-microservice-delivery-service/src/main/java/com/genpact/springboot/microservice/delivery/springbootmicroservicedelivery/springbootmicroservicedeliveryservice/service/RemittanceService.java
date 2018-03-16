package com.genpact.springboot.microservice.delivery.springbootmicroservicedelivery.springbootmicroservicedeliveryservice.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.genpact.springboot.microservice.delivery.springbootmicroservicedelivery.springbootmicroservicedeliveryservice.model.Remittance;

public interface RemittanceService extends BaseService<Remittance,Long>{
	
	List<Remittance> getAllByConduitCode(String code);	
}
