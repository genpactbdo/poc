package com.genpact.springboot.microservice.delivery.springbootmicroservicedelivery.springbootmicroservicedeliveryservice.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.genpact.springboot.microservice.delivery.springbootmicroservicedelivery.springbootmicroservicedeliveryservice.model.Remittance;

public interface RemittanceRepository extends JpaRepository<Remittance,Long> {
	
	@Query("SELECT dr FROM Remittance dr WHERE dr.conduit = :code")
	List<Remittance> getAllByConduitCode(@Param("code") String code);	

}
