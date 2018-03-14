package com.genpact.springboot.microservice.delivery.springbootmicroservicedelivery.springbootmicroservicedeliveryservice.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DeliveryController {

	private static final String FILE_PATH = "temp.log";
	
	@RequestMapping("/download_delivery")
	public ResponseEntity<Resource> downloadDeliveryFile() throws IOException {
		
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
