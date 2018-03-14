package com.genpact.springboot.microservice.authorization.springbootmicroserviceauthorizationservice.repository;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.genpact.springboot.microservice.authorization.springbootmicroserviceauthorizationservice.model.Remittance;
 
@RepositoryRestResource
public interface RemittanceRepository extends CrudRepository<Remittance, Long> {

	List<Remittance> findAllByStatus(String status);
	List<Remittance> findAllByConduit(String conduit);
	
}