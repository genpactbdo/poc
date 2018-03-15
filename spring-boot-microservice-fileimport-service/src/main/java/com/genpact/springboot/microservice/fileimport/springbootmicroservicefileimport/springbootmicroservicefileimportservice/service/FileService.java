package com.genpact.springboot.microservice.fileimport.springbootmicroservicefileimport.springbootmicroservicefileimportservice.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.genpact.springboot.microservice.fileimport.springbootmicroservicefileimport.springbootmicroservicefileimportservice.model.Remittance;

@SuppressWarnings("rawtypes")
public interface FileService extends BaseService{

	List<Remittance> parseUploadFile(MultipartFile file) throws FileNotFoundException, IOException, NumberFormatException, ParseException;
	void parseFileFromFolder();
	void sendRemittanceJson(String url, Remittance remittance);
}
