package com.genpact.springboot.microservice.fileimport.springbootmicroservicefileimport.springbootmicroservicefileimportservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.genpact.springboot.microservice.fileimport.springbootmicroservicefileimport.springbootmicroservicefileimportservice.model.Conduit;

@Service("conduitService")
public class ConduitServiceImpl extends BaseServiceImpl<Conduit,Long> implements ConduitService {

	public ConduitServiceImpl() {
		
	}
	
	@Autowired
	public ConduitServiceImpl(JpaRepository<Conduit,Long> jpaRepository ) {
		super(jpaRepository);		
	}

}
