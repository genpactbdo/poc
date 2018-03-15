package com.genpact.springboot.microservice.delivery.springbootmicroservicedelivery.springbootmicroservicedeliveryservice.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.genpact.springboot.microservice.delivery.springbootmicroservicedelivery.springbootmicroservicedeliveryservice.FileParser;
import com.genpact.springboot.microservice.delivery.springbootmicroservicedelivery.springbootmicroservicedeliveryservice.jpa.RemittanceRepository;
import com.genpact.springboot.microservice.delivery.springbootmicroservicedelivery.springbootmicroservicedeliveryservice.model.Feedback;
import com.genpact.springboot.microservice.delivery.springbootmicroservicedelivery.springbootmicroservicedeliveryservice.model.Remittance;

@Service("remittanceService")
public class RemittanceServiceImpl extends BaseServiceImpl<Remittance,Long> implements RemittanceService{

	@Autowired
	RemittanceRepository remittanceRepository;
	
	public RemittanceServiceImpl(JpaRepository<Remittance,Long> jpaRepository) {
		super(jpaRepository);		
	}

	@Override
	public List<Remittance> getAllByConduitCode(String code) {
		// TODO Auto-generated method stub
		return remittanceRepository.getAllByConduitCode(code);
	}

}
