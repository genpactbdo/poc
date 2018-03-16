package com.genpact.springboot.microservice.delivery.springbootmicroservicedelivery.springbootmicroservicedeliveryservice.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.genpact.springboot.microservice.delivery.springbootmicroservicedelivery.springbootmicroservicedeliveryservice.model.Remittance;
import com.genpact.springboot.microservice.delivery.springbootmicroservicedelivery.springbootmicroservicedeliveryservice.service.RemittanceService;

@Controller
@RequestMapping("/delivery")
public class DeliveryController {

	private static final String FILE_PATH = "temp.log";
	private static final String DOOR_TO_DOOR = "DTD";
	
	@Autowired
	private RemittanceService remittanceService;
	
	
	@RequestMapping("")
	public String displayDeliverylist(Model model) {
		model.addAttribute("deliveryList", remittanceService.getAll());
		return "deliveryList";
	}
	
	@RequestMapping("/download_delivery")
	public ResponseEntity<Resource> downloadDeliveryFile(@RequestParam(required = false) String conduit) throws IOException {
		List<Remittance> list = null;
		
		if(conduit != null && !conduit.isEmpty()) {
			list = remittanceService.getAllByConduitCode(conduit);
		}else {
			list = remittanceService.getAll();
		}		 

		PrintWriter writer = new PrintWriter(FILE_PATH, "UTF-8");
		StringBuilder sb = new StringBuilder();
		
		for(Remittance r : list) {					
			sb.append(printRemittanceDetail(r.getConduit()) + "|");
			sb.append(printRemittanceDetail(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(r.getDate())) + "|");
			sb.append(printRemittanceDetail(r.getTransactionType()) + "|");
			sb.append(printRemittanceDetail(r.getProductType()) + "|");
			sb.append(printRemittanceDetail(r.getRefNo()) + "|");
			sb.append(printRemittanceDetail(r.getRemitterCode()) + "|");
			sb.append(printRemittanceDetail(r.getRemitterName()) + "|");			
			sb.append(printRemittanceDetail(r.getAddress1()) + "|");
			sb.append(printRemittanceDetail(r.getCityTown()) + "|");
			sb.append(printRemittanceDetail(r.getStateProvince()) + "|");
			sb.append(printRemittanceDetail(r.getCountry()) + "|");
			sb.append(printRemittanceDetail(r.getBeneficiaryCode()) + "|");
			sb.append(printRemittanceDetail(r.getBeneficiaryName()) + "|");
			sb.append(printRemittanceDetail(new SimpleDateFormat("d-MMM-yy").format(r.getBeneficiaryDateOfBirth())) + "|");
			sb.append(printRemittanceDetail(r.getBeneficiaryBank()) + "|");
			sb.append(printRemittanceDetail(r.getBeneficiaryBankBranch()) + "|");
			sb.append(printRemittanceDetail(r.getBeneficiaryBankAccountNo()) + "|");	
			sb.append(printRemittanceDetail(r.getSourceAmount()) + "|");
			sb.append(printRemittanceDetail(r.getSourceCurrency()) + "|");
			sb.append(printRemittanceDetail(r.getBeneficiaryAddress1()) + "|");
			sb.append(printRemittanceDetail(r.getBeneficiaryCityTown()) + "|");
			sb.append(printRemittanceDetail(r.getBeneficiaryStateProvince()) + "|");
			sb.append(printRemittanceDetail(r.getBeneficiaryCountry()) + "|"); 
			sb.append(printRemittanceDetail(r.getStatus()));
						
			writer.println(sb.toString());		
			
			sb = new StringBuilder();
		}			
		 
		writer.close();
		  
		File file = new File("temp.log");
	  	InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
	  	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMMdd");		
		
		String conduitName = conduit != null ? conduit : "DEFAULT";
		String dateToday = sdf.format(new Date());
		String fileExtension = "txt";
	  
		String fileName = conduitName + "_" + dateToday + "." + fileExtension;
				  				  
	    return ResponseEntity.ok()
	            .contentLength(file.length())
	            .contentType(MediaType.parseMediaType("application/octet-stream"))
	            .header("content-disposition", "attachment; filename=" + fileName)
	            .body(resource);
	}  

	@RequestMapping(value="/add_delivery",method=RequestMethod.POST)
	public ResponseEntity<Resource> add(@RequestBody Remittance remittance) {
		
		//Segregation of CTA and DTD happens here.
		if(remittance != null) {
			if(DOOR_TO_DOOR.equals(remittance.getTransactionType())) {
				remittance.setStatus("PENDING DELIVERY");
			}else{
				remittance.setStatus("REMITTED");
			}
			
			remittanceService.add(remittance);
		}					
		
		return ResponseEntity.ok().build();
	} 
	
	
	@RequestMapping("/add_delivery_test")
	public String addDelivery(@RequestParam(defaultValue = "") String type, Model model) {
		Remittance r = new Remittance();		
		
		r.setConduit("BDO Remit (USA) Inc.");
		r.setDate(new Date());
		r.setTransactionType(type);
		r.setProductType("BDO");
		r.setRefNo("0000087876");
		r.setRemitterCode("1800000001");
		r.setRemitterName("Ana Bel");
		r.setRemitterGender("F");
		r.setCivilStatus("M");
		r.setDateOfBirth(new Date());		
		r.setAddress1("260-C North El Camino Real");
		r.setAddress2("");
		r.setCityTown("Encinitas");
		r.setStateProvince("CA");
		r.setPostalCode("92024-2852");
		r.setCountry("USA");
		r.setTelNo("18001111111");
		r.setBeneficiaryCode("63000001");
		r.setBeneficiaryName("Gloria Bel");
		r.setBeneficiaryGender("F");
		r.setBeneficiaryCivilStatus("M");
		r.setBeneficiaryDateOfBirth(new Date());
		r.setBeneficiaryBank("BDO");
		r.setBeneficiaryBankAccountNo("000654321234");
		r.setBeneficiaryBankAccountCurrency("PHP");
		r.setBeneficiaryBankBranch("A Place - Coral Way");
		r.setSourceCurrency("USD");
		r.setSourceAmount(500.00);
		r.setBeneficiaryAddress1("21 Rizal St. Pio del Pilar");
		r.setBeneficiaryAddress2("");
		r.setBeneficiaryCityTown("Makati City");
		r.setBeneficiaryStateProvince("Metro Manila");
		r.setBeneficiaryPostalCode("1400");
		r.setBeneficiaryCountry("Philippines");
		r.setStatus("PENDING DELIVERY");
		
		remittanceService.add(r);
		
		model.addAttribute("deliveryList", remittanceService.getAll());
		
		return "redirect:/delivery";
		//return remittanceService.getAll();
	}
	
	private String printRemittanceDetail(Object obj) {
		return obj != null ? obj.toString() : "";
	}
	
	@RequestMapping("/get_deliveries")
	public @ResponseBody List<Remittance> getAllRemittances(){
		return remittanceService.getAll();
	}
	
	/*@RequestMapping("/download_delivery")
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
	  
	}*/  
	
}
