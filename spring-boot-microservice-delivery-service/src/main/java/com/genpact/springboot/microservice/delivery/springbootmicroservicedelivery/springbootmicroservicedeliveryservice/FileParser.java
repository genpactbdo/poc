package com.genpact.springboot.microservice.delivery.springbootmicroservicedelivery.springbootmicroservicedeliveryservice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import com.genpact.springboot.microservice.delivery.springbootmicroservicedelivery.springbootmicroservicedeliveryservice.model.Feedback;
import com.genpact.springboot.microservice.delivery.springbootmicroservicedelivery.springbootmicroservicedeliveryservice.model.Remittance;

public class FileParser {

	Logger logger = Logger.getAnonymousLogger();
			
	private File file;		
	
	public FileParser(File file) {
		this.file = file;
	}
	
	public List<Feedback> parseFeedback() throws NumberFormatException, IOException, ParseException  {
		List<Feedback> feedbackList = new ArrayList<Feedback>();
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		
		fileReader = new FileReader(file);			
		bufferedReader = new BufferedReader(fileReader);	
		
		String string = null;

		while((string = bufferedReader.readLine()) != null) {			
			String[] values = string.split("\\|");
			
			Feedback r = new Feedback();
								
			r.setConduit(values[0]);
			r.setDate(convertTransactionDateFormat(values[1]));
			r.setTransactionType(values[2]);
			r.setProductType(values[3]);
			r.setRefNo(values[4]);
			r.setRemitterCode(values[5]);
			r.setRemitterName(values[6]);
			r.setAddress1(values[7]);
			r.setCityTown(values[8]);
			r.setStateProvince(values[9]);
			r.setCountry(values[10]);
			r.setBeneficiaryCode(values[11]);
			r.setBeneficiaryName(values[12]);
			r.setBeneficiaryDateOfBirth(convertBirthDateFormat(values[13]));
			r.setBeneficiaryBank(values[14]);
			r.setBeneficiaryBankBranch(values[15]);
			r.setBeneficiaryBankAccountNo(values[16]);		
			r.setSourceAmount(Double.parseDouble(values[17]));
			r.setSourceCurrency(values[18]);
			r.setBeneficiaryAddress1(values[19]);
			r.setBeneficiaryCityTown(values[20]);
			r.setBeneficiaryStateProvince(values[21]);
			r.setBeneficiaryCountry(values[22]);
			r.setStatus(values[23]);			
			
			feedbackList.add(r);						
		}
		
		bufferedReader.close();
				
		return feedbackList;
	}
	
	private Date convertTransactionDateFormat(String date) throws ParseException {	
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");		
		return sdf.parse(date);
	}
	
	private Date convertBirthDateFormat(String date) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("d-MMM-yy");
		return sdf.parse(date);
	}
}
