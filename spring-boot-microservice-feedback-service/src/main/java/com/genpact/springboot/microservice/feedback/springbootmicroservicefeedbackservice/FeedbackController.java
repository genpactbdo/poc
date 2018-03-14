package com.genpact.springboot.microservice.feedback.springbootmicroservicefeedbackservice;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class FeedbackController {

	private static final String FILE_PATH = "temp.log";
	
  @GetMapping(value = "/feedback")
  public ResponseEntity<Resource> createFeedbackFile() throws IOException {
	  
	  PrintWriter writer = new PrintWriter(FILE_PATH, "UTF-8");
	  writer.println("ML,DTD,5000,OK");
	  writer.println("SB,CTA,3000,NOK");
	  writer.close();
	  
	  File file = new File("temp.log");
	  
	  InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
	  
	  String conduitName = "ML";
	  String dateToday = "2018MAR13";
	  String fileExtension = "txt";
	  
	  String fileName = conduitName + "_" + dateToday + "." + fileExtension;
			  
			  
	    return ResponseEntity.ok()
	            .contentLength(file.length())
	            .contentType(MediaType.parseMediaType("application/octet-stream"))
	            .header("content-disposition", "attachment; filename=" + fileName)
	            .body(resource);
	  
  }
  
}