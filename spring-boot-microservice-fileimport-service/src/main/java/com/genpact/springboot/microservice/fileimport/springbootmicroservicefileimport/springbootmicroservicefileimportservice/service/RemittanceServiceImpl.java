package com.genpact.springboot.microservice.fileimport.springbootmicroservicefileimport.springbootmicroservicefileimportservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.genpact.springboot.microservice.fileimport.springbootmicroservicefileimport.springbootmicroservicefileimportservice.model.Remittance;

@Service("remittanceService")
public class RemittanceServiceImpl extends BaseServiceImpl<Remittance,Long> implements RemittanceService {

	public RemittanceServiceImpl() {
		
	}
	
	@Autowired
	public RemittanceServiceImpl(JpaRepository<Remittance,Long> jpaRepository) {
		super(jpaRepository);
	}
	
}
