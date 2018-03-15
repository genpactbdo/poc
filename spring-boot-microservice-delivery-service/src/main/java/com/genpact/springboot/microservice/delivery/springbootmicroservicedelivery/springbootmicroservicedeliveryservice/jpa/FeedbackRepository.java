package com.genpact.springboot.microservice.delivery.springbootmicroservicedelivery.springbootmicroservicedeliveryservice.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.genpact.springboot.microservice.delivery.springbootmicroservicedelivery.springbootmicroservicedeliveryservice.model.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

}
