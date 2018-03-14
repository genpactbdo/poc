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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.genpact.springboot.microservice.authorization.springbootmicroserviceauthorizationservice.model.Remittance;
import com.genpact.springboot.microservice.authorization.springbootmicroserviceauthorizationservice.repository.RemittanceRepository;

@Controller
public class AuthorizationController {

	private final static String OPEN = "O";
	private final static String PROCESSED = "P";
	
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
    public String handleManualAuthorize(RedirectAttributes redirectAttributes) {

    	RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Remittance data = new Remittance();
        data.setConduit("SB");
        data.setSourceAmount(7500d);
        HttpEntity<?> entity = new HttpEntity<Object>(data,headers);
        ResponseEntity<Object> responseEntity =    restTemplate.exchange("http://localhost:8200/authorize", HttpMethod.POST, entity, Object.class);
        
        System.out.println(responseEntity);
        
        redirectAttributes.addFlashAttribute("message",
                "You successfully added a transaction!");

        return "redirect:/";
    }
    
    @RequestMapping(value="/authorize",method=RequestMethod.POST)
    public ResponseEntity<Resource> authorize(@RequestBody Remittance remittance){
    	
    	remittance.setStatus(OPEN);
    	System.out.println(remittance.getId());
    	repository.save(remittance);
    	
    	return ResponseEntity.ok().build();
    	
    }

}