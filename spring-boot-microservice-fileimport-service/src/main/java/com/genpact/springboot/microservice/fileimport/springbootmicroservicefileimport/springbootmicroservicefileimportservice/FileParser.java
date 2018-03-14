package com.genpact.springboot.microservice.fileimport.springbootmicroservicefileimport.springbootmicroservicefileimportservice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import com.genpact.springboot.microservice.fileimport.springbootmicroservicefileimport.springbootmicroservicefileimportservice.model.Conduit;
import com.genpact.springboot.microservice.fileimport.springbootmicroservicefileimport.springbootmicroservicefileimportservice.model.Remittance;

public class FileParser {

	Logger logger = Logger.getAnonymousLogger();
			
	private File file;		
	
	public FileParser(File file) {
		this.file = file;
	}
	
	public List<Remittance> parseRemittance() throws NumberFormatException, IOException, ParseException  {
		List<Remittance> remittanceList = new ArrayList<Remittance>();
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		
		fileReader = new FileReader(file);			
		bufferedReader = new BufferedReader(fileReader);
		
		String string = "";
		
		while((string = bufferedReader.readLine()) != null) {		
			String[] values = string.split("\\|");		
			
			Remittance remittance = new Remittance();
			Conduit conduit = new Conduit();
			conduit.setId(Long.parseLong(values[0]));
			
			remittance.setConduitId(conduit);	
			remittance.setReferenceNumber(values[1]);
			remittance.setTransactionDate(convertDateFormat(values[2]));
			remittance.setSenderName(values[3]);
			remittance.setBeneficiaryName(values[4]);
			remittance.setBeneficiaryAddress(values[5]);
			remittance.setSentAmount(new BigDecimal(values[6]));
			remittance.setSentCurrency(Integer.parseInt(values[7]));
			remittance.setPayoutAmount(new BigDecimal(values[8]));
			remittance.setPayoutCurrency(Integer.parseInt(values[9]));
			remittance.setTransactionType(Integer.parseInt(values[10]));
			remittance.setStatus(Integer.parseInt(values[11]));
			remittance.setCreatedDate(new Date());
			remittance.setLastUpdatedDate(new Date());
			
			remittanceList.add(remittance);						
		}
		
		bufferedReader.close();
				
		return remittanceList;
	}
	
	private Date convertDateFormat(String date) throws ParseException {	
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");		
		return sdf.parse(date);
	}
}
