package com.genpact.springboot.microservice.delivery.springbootmicroservicedelivery.springbootmicroservicedeliveryservice.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.genpact.springboot.microservice.delivery.springbootmicroservicedelivery.springbootmicroservicedeliveryservice.model.Feedback;
import com.genpact.springboot.microservice.delivery.springbootmicroservicedelivery.springbootmicroservicedeliveryservice.service.FeedbackService;

@Controller
@RequestMapping("/feedback")
public class FeedbackController {

	@Autowired
	private FeedbackService feedbackService;	
	
	
	@RequestMapping("")
	public String index(Model model) {		
		return "fileUpload";
	}
	
	@RequestMapping("/upload_feedback")
	public String uploadFeedbackFile(MultipartFile file, RedirectAttributes redirectAttributes, Model model) throws IOException {
		if(file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
			return "redirect:/feedback";
		}
		
		try {			
			List<Feedback> list = feedbackService.parseFeedbackFile(file);			
			feedbackService.saveAll(list);
			model.addAttribute("message", "You have successfully uploaded '" + file.getOriginalFilename() + "'");					
		}catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "An error occurred while uploading the file.");
		}
				
		return "fileUploadStatus";	
	} 	
		
	@RequestMapping("/feedback_list")
	public String displayFeedbackList(Model model) {
		model.addAttribute("feedbackList", feedbackService.getAll());
		return "feedbackList";
	}
}
