package com.genpact.springboot.microservice.delivery.springbootmicroservicedelivery.springbootmicroservicedeliveryservice.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.genpact.springboot.microservice.delivery.springbootmicroservicedelivery.springbootmicroservicedeliveryservice.model.Feedback;
import com.genpact.springboot.microservice.delivery.springbootmicroservicedelivery.springbootmicroservicedeliveryservice.model.Remittance;

public interface FeedbackService extends BaseService<Feedback,Long>{

	List<Feedback> parseFeedbackFile(MultipartFile file) throws IOException, NumberFormatException, ParseException;
}
