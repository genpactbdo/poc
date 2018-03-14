package com.genpact.springboot.microservice.fileimport.springbootmicroservicefileimport.springbootmicroservicefileimportservice.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.genpact.springboot.microservice.fileimport.springbootmicroservicefileimport.springbootmicroservicefileimportservice.model.Conduit;

public interface ConduitRepository extends JpaRepository<Conduit, Long> {

}
