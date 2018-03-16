package com.genpact.springboot.microservice.fileimport.springbootmicroservicefileimport.springbootmicroservicefileimportservice.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.genpact.springboot.microservice.fileimport.springbootmicroservicefileimport.springbootmicroservicefileimportservice.FileParser;
import com.genpact.springboot.microservice.fileimport.springbootmicroservicefileimport.springbootmicroservicefileimportservice.jpa.ConduitRepository;
import com.genpact.springboot.microservice.fileimport.springbootmicroservicefileimport.springbootmicroservicefileimportservice.jpa.RemittanceRepository;
import com.genpact.springboot.microservice.fileimport.springbootmicroservicefileimport.springbootmicroservicefileimportservice.model.Conduit;
import com.genpact.springboot.microservice.fileimport.springbootmicroservicefileimport.springbootmicroservicefileimportservice.model.Remittance;
import com.genpact.springboot.microservice.fileimport.springbootmicroservicefileimport.springbootmicroservicefileimportservice.service.ConduitService;
import com.genpact.springboot.microservice.fileimport.springbootmicroservicefileimport.springbootmicroservicefileimportservice.service.FileService;
import com.genpact.springboot.microservice.fileimport.springbootmicroservicefileimport.springbootmicroservicefileimportservice.service.RemittanceService;

@Controller
public class FileImportController {
	
	private final String FILEPATH = "D:\\Users\\User1\\Downloads\\spring-boot-microservice-fileimport-service\\spring-boot-microservice-fileimport-service\\src\\main\\resources\\testFile.file";
	
	@Autowired
	private RemittanceService remittanceService;
	
	@Autowired
	private ConduitService conduitService;
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private Environment environment;
	
	@RequestMapping("/")
	public String index() {
		return "fileUpload";
	}
	
	/*@RequestMapping("/upload")
	public String upload(@RequestParam(name="file") MultipartFile file, RedirectAttributes redirectAttributes, Model model) {
		
		if(file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
			return "redirect:/upload_status";
		}
		
		try {			
			List<Remittance> list = fileService.parseUploadFile(file);
			remittanceService.saveAll(list);
			model.addAttribute("message", "You have successfully uploaded '" + file.getOriginalFilename() + "'");					
		}catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "An error occurred while uploading the file.");
		}
				
		return "fileUploadStatus";
	}*/
	
	@RequestMapping("/upload_status")
	public String uploadStatus() {
		return "fileUploadStatus";
	}
	
	
	@RequestMapping("/get_remittances")
	public @ResponseBody List<Remittance> getRemittanceList(){
		return remittanceService.getAll();
	}

	@RequestMapping("/get_conduits")
	public @ResponseBody List<Conduit> getConduitList(){
		return conduitService.getAll();
	}
	
	@RequestMapping("/upload")
	public String test(@RequestParam(name="file") MultipartFile file, Model model){		
		try {
			List<Remittance> list = fileService.parseUploadFile(file);
			
			for(Remittance remittance : list) {
				fileService.sendRemittanceJson("http://localhost:8200/authorize", remittance);
				System.out.println("Remittance sent!");
			}
						
			System.out.println("Remittance sending successful!");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("message", "You have successfully uploaded '" + file.getOriginalFilename() + "'");
		return "fileUploadStatus";
	}
	
	
		
	/*@RequestMapping("/test_import_file")
	public String importFileTest() {
		File file = new File(FILEPATH);
		FileParser fileParser = new FileParser(file);
		List<Remittance> list = fileParser.parseRemittance();
		
		StringBuilder sb = new StringBuilder();
		int count = 1;
		
		for(Remittance r : list) {
			sb.append("Remittance " + count);
			sb.append("<br>Conduit ID: " + r.getConduitId());
			sb.append("<br>Reference Number: " + r.getSenderName());
			sb.append("<br>Transaction Date " + r.getTransactionDate().toString());
			sb.append("<br>Sender Name: " + r.getSenderName());
			sb.append("<br>Beneficiary Name: " + r.getBeneficiaryName());
			sb.append("<br>Beneficiary Address: " + r.getBeneficiaryAddress());
			sb.append("<br>Sent Amount: " + r.getSentAmount());
			sb.append("<br>Sent Currency: " + r.getSentCurrency());
			sb.append("<br>Payout Amount: " + r.getPayoutAmount());
			sb.append("<br>Transaction Type: " + r.getTransactionType());
			sb.append("<br>Status: " + r.getStatus());	
			sb.append("<br><br>");
			
			count++;
		}
		
		return sb.toString();
	}*/
	
}
