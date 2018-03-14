package com.genpact.springboot.microservice.authorization.springbootmicroserviceauthorizationservice;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.genpact.springboot.microservice.authorization.springbootmicroserviceauthorizationservice.model.Remittance;
import com.genpact.springboot.microservice.authorization.springbootmicroserviceauthorizationservice.repository.RemittanceRepository;

@Controller
public class AuthorizationController {

	private final static String OPEN = "O";
	
	@Autowired
	private RemittanceRepository repository;
	
    @GetMapping("/")
    public String listRemittances(Model model) throws IOException {
    	List<Remittance> remittances = repository.findAllByStatus(OPEN);
    	model.addAttribute("remittances", remittances);
        return "authorizationForm";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) throws FileNotFoundException, UnsupportedEncodingException {
    	PrintWriter writer = new PrintWriter("test.txt", "UTF-8");
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

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }


}