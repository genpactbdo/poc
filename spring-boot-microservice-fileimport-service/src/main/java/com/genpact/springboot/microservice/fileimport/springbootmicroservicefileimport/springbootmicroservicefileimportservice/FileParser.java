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
		
		String string = null;

		while((string = bufferedReader.readLine()) != null) {			
			String[] values = string.split("\\|");
			
			Remittance r = new Remittance();
			
			r.setConduit(values[0]);
			r.setDate(convertTransactionDateFormat(values[1]));
			r.setTransactionType(values[2]);
			r.setProductType(values[3]);
			r.setRefNo(values[4]);
			r.setRemitterCode(values[5]);
			r.setRemitterName(values[6]);
			r.setRemitterGender(values[7]);
			r.setCivilStatus(values[8]);
			r.setDateOfBirth(convertBirthDateFormat(values[9]));
			r.setAddress1(values[10]);
			r.setAddress2(values[11]);
			r.setCityTown(values[12]);
			r.setStateProvince(values[13]);
			r.setPostalCode(values[14]);
			r.setCountry(values[15]);
			r.setTelNo(values[16]);
			r.setBeneficiaryCode(values[17]);
			r.setBeneficiaryName(values[18]);
			r.setBeneficiaryGender(values[19]);
			r.setBeneficiaryCivilStatus(values[20]);
			r.setBeneficiaryDateOfBirth(convertBirthDateFormat(values[21]));
			r.setBeneficiaryBank(values[22]);
			r.setBeneficiaryBankAccountNo(values[23]);
			r.setBeneficiaryBankAccountCurrency(values[24]);
			r.setBeneficiaryBankBranch(values[25]);
			r.setSourceCurrency(values[26]);
			r.setSourceAmount(Double.parseDouble(values[27]));
			r.setBeneficiaryAddress1(values[28]);
			r.setBeneficiaryAddress2(values[29]);
			r.setBeneficiaryCityTown(values[30]);
			r.setBeneficiaryStateProvince(values[31]);
			r.setBeneficiaryPostalCode(values[32]);
			r.setBeneficiaryCountry(values[33]);
			
			remittanceList.add(r);						
		}
		
		bufferedReader.close();
				
		return remittanceList;
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
