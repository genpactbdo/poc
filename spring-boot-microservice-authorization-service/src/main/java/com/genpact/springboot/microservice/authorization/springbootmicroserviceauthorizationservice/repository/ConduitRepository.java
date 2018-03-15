package com.genpact.springboot.microservice.authorization.springbootmicroserviceauthorizationservice.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.genpact.springboot.microservice.authorization.springbootmicroserviceauthorizationservice.model.Conduit;
 
@RepositoryRestResource
public interface ConduitRepository extends CrudRepository<Conduit, Long> {

	Conduit findByConduitName(String conduitName);
	
}