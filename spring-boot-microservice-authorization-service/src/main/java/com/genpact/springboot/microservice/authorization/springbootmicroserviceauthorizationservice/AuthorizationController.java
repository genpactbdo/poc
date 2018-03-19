package com.genpact.springboot.microservice.authorization.springbootmicroserviceauthorizationservice;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.LongStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.genpact.springboot.microservice.authorization.springbootmicroserviceauthorizationservice.model.Conduit;
import com.genpact.springboot.microservice.authorization.springbootmicroserviceauthorizationservice.model.Remittance;
import com.genpact.springboot.microservice.authorization.springbootmicroserviceauthorizationservice.repository.ConduitRepository;
import com.genpact.springboot.microservice.authorization.springbootmicroserviceauthorizationservice.repository.RemittanceRepository;

@Controller
public class AuthorizationController {

	private final static String OPEN = "O";
	private final static String PROCESSED = "P";
	private final static String COLON_DELIMITER = ":";
	
	@Autowired
	private RemittanceRepository repository;
	
	@Autowired
	private ConduitRepository conduitRep;
	
	@Autowired
	private Environment environment;
	
    @GetMapping("/conduit")
    public String listConduits(Model model, @ModelAttribute ConduitForm conduitForm) throws IOException {
    	
    	Iterable<Conduit> conduitIterable = conduitRep.findAll();
    	ArrayList<Conduit> conduits = new ArrayList<>();
    	conduitIterable.forEach(conduits::add);
    	conduitForm.setConduits(conduits);
    	
    	model.addAttribute("conduitForm", conduitForm);
    	
        return "conduitForm";
    }
    
    @PostMapping("/conduit")
    public String updateConduits(Model model, @ModelAttribute ConduitForm conduitForm) throws IOException {
    	
    	for(Conduit conduit : conduitForm.getConduits()) {
    		if(LongStream.of(conduitForm.getIdSelectedConduits()).anyMatch(x -> x == conduit.getId())) {
    			conduitRep.save(conduit);
    		}
    	}
    	
    	System.out.println(Arrays.toString(conduitForm.getIdSelectedConduits()));
    	
    	for(long id : conduitForm.getIdSelectedConduits()) {
    		System.out.println("id: " + id);
    	}
    	
    	Iterable<Conduit> conduitIterable = conduitRep.findAll();
    	ArrayList<Conduit> conduits = new ArrayList<>();
    	conduitIterable.forEach(conduits::add);
    	conduitForm.setConduits(conduits);
    	
    	model.addAttribute("conduitForm", conduitForm);
    	
        return "conduitForm";
    }
    
    @GetMapping("/authorize/{conduit}")
    public String listRemittances(@PathVariable String conduit, Model model) throws IOException {
    	RemittanceForm remittanceForm = new RemittanceForm();
    	ArrayList<Remittance> remittances = repository.findByConduitAndStatus(conduit, OPEN);
    	System.out.println("remittances: " + remittances.size());
    	remittanceForm.setRemittances(remittances);
		remittanceForm.setConduitName(conduit);
    	
    	model.addAttribute("remittanceForm", remittanceForm);
    	
        return "authorizationForm";
    }

    @PostMapping("/authorize/{conduit}")
    public String handleManualAuthorize(@PathVariable String conduit, @ModelAttribute RemittanceForm remittanceForm, 
    			Model model, RedirectAttributes redirectAttributes) {

    	System.out.println("authorization running on port: " + environment.getProperty("local.server.port"));
    	
    	String message = "";
    	
    	String[] entry;
    	ArrayList<String[]> entryList = new ArrayList<String[]>();
    	
    	double toAuthorizeAmount = 0d;
    	
    	String[] selEntries = remittanceForm.getIdSelectedAmountEntries();
    	if(selEntries != null) {
	    	for(String d : remittanceForm.getIdSelectedAmountEntries()) {
	    		entry = d.split(COLON_DELIMITER);
	    		entryList.add(entry);
	    		toAuthorizeAmount += Double.valueOf(entry[1]);
	    	}
    	}
    	
    	double amount = 0d;
    	if(remittanceForm.getAuthorizedAmount() != null) {
    		amount += remittanceForm.getAuthorizedAmount();
    	}
    	
    	if(toAuthorizeAmount <= 0d) {
    		message += "No remittance to authorize, please select one";
    	} else if(toAuthorizeAmount <= amount) {
    		
    		Conduit conduitObj = conduitRep.findByConduitName(conduit);
    		
    		if(conduitObj != null) {
	    		double bal = conduitObj.getBalance();
	    		double limit = conduitObj.getCreditLimit();
	    		double total = conduitObj.getTotalRemit();
	    		
	    		double currentLimit = getCurrentLimit(bal, limit, total);
	    		
	    		if(toAuthorizeAmount <= currentLimit) {
	    			
	    			
	    			bal = bal - toAuthorizeAmount;
	    			total += toAuthorizeAmount;
	    			
	    			if(bal < 0d) {
	    				bal = 0d;
	    			}
	    			conduitObj.setBalance(bal);
	    			conduitObj.setTotalRemit(total);
	    			conduitRep.save(conduitObj);
	    			
	    			for(String[] remittance : entryList) {
	    				long remId = Long.valueOf(remittance[0]);
	    				Remittance rem = repository.findById(remId).get();
	    				rem.setStatus(PROCESSED);
	    				repository.save(rem);
	    				sendRemittanceJson("http://localhost:8500/delivery/add_delivery", rem);
	    			}
	    			
	    			redirectAttributes.addFlashAttribute("message", "Authorization successful");
	    			return "redirect:/authorize/" + conduit;
	    		} else {
	    			message += "Total amount to authorize: " + toAuthorizeAmount 
	        				+ " is greater than current conduit credit capacity of " + currentLimit;
	    		}
	    		
    		} else {
    			message += "No conduit: '" + conduit + "' is seen in database";
    		}
    		
    	} else {
    		message += "Total amount to authorize: " + toAuthorizeAmount 
    				+ " is greater than entered amount: " + amount;
    	}
    	
    	model.addAttribute("message", message);
    	return "authorizationForm";
    }

	private double getCurrentLimit(double bal, double limit, double total) {
		return bal + limit - total;
	}
    
    @RequestMapping(value="/authorize",method=RequestMethod.POST)
    public ResponseEntity<Resource> authorize(@RequestBody Remittance remittance){
    	
    	Conduit conduitObj = conduitRep.findByConduitName(remittance.getConduit());
    	
    	double amount = remittance.getSourceAmount();
    	
    	if(conduitObj == null) {
    		return ResponseEntity.unprocessableEntity().build();
    	}
    	double bal = conduitObj.getBalance();
		double limit = conduitObj.getCreditLimit();
		double total = conduitObj.getTotalRemit();
		
		double currentLimit = getCurrentLimit(bal, limit, total);
		
		double remainingAmount = currentLimit - amount;
		if(remainingAmount < 0) {
			
			remittance.setStatus(OPEN);
			repository.save(remittance);
			
		} else {
			
			bal -= amount;
			if(bal < 0d) {
				bal = 0d;
			}
			
			conduitObj.setBalance(bal);
			conduitObj.setTotalRemit(total + amount);
			conduitRep.save(conduitObj);
			
			remittance.setStatus(PROCESSED);
	    	repository.save(remittance);
	    	
	    	// call delivery
	    	sendRemittanceJson("http://localhost:8500/delivery/add_delivery", remittance);
		}
    	
		System.out.println("authorization server run on port: " + environment.getProperty("local.server.port"));
    	return ResponseEntity.ok().build();
    	
    }
    
    public void sendRemittanceJson(String url, Remittance remittance) {
		// TODO Auto-generated method stub
		RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);       
        HttpEntity<?> entity = new HttpEntity<Object>(remittance,headers);
        ResponseEntity<Object> responseEntity =  restTemplate.exchange(url, HttpMethod.POST, entity, Object.class);       
	}

}