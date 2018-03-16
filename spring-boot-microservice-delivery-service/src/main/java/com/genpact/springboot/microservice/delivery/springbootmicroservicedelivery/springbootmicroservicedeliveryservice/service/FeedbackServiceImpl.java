package com.genpact.springboot.microservice.delivery.springbootmicroservicedelivery.springbootmicroservicedeliveryservice.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.genpact.springboot.microservice.delivery.springbootmicroservicedelivery.springbootmicroservicedeliveryservice.FileParser;
import com.genpact.springboot.microservice.delivery.springbootmicroservicedelivery.springbootmicroservicedeliveryservice.jpa.FeedbackRepository;
import com.genpact.springboot.microservice.delivery.springbootmicroservicedelivery.springbootmicroservicedeliveryservice.model.Feedback;

@Service("feedbackService")
public class FeedbackServiceImpl extends BaseServiceImpl<Feedback,Long> implements FeedbackService {

	@Autowired
	FeedbackRepository feedbackRepository;
	
	public FeedbackServiceImpl(JpaRepository<Feedback,Long> feedbackRepository) {
		super(feedbackRepository);
	}
	
	@Override
	public List<Feedback> parseFeedbackFile(MultipartFile file)
			throws IOException, NumberFormatException, ParseException {
		// TODO Auto-generated method stub
		File file2 = new File(file.getOriginalFilename());
		FileOutputStream fos = new FileOutputStream(file2);
		fos.write(file.getBytes());
		fos.close();
		
		FileParser fileParser = new FileParser(file2);
		List<Feedback> list = fileParser.parseFeedback();			
		
		return list;
	}

	
}
