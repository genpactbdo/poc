package com.genpact.springboot.microservice.delivery.springbootmicroservicedelivery.springbootmicroservicedeliveryservice.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.genpact.springboot.microservice.delivery.springbootmicroservicedelivery.springbootmicroservicedeliveryservice.model.Remittance;

public interface RemittanceRepository extends JpaRepository<Remittance,Long>{

}
