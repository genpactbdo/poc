package com.genpact.springboot.microservice.delivery.springbootmicroservicedelivery.springbootmicroservicedeliveryservice.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DeliveryController {

	@RequestMapping("/download_delivery")
	public void downloadDeliveryFile(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		response.setHeader("Content-disposition", "attachment;filename=download.txt");
		response.setContentType("text/plain");
		
		
	}  
}
