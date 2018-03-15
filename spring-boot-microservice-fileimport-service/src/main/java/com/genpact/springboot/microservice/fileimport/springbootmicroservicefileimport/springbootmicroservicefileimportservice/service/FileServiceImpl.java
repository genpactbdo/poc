package com.genpact.springboot.microservice.fileimport.springbootmicroservicefileimport.springbootmicroservicefileimportservice.service;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;
import static java.nio.file.StandardWatchEventKinds.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.genpact.springboot.microservice.fileimport.springbootmicroservicefileimport.springbootmicroservicefileimportservice.FileParser;
import com.genpact.springboot.microservice.fileimport.springbootmicroservicefileimport.springbootmicroservicefileimportservice.model.Remittance;

@Service("fileService")
public class FileServiceImpl extends BaseServiceImpl implements FileService{

	private JpaRepository<Remittance,Long> remittanceRepository;
	
	private final String FILEPATH = "listenFolder";
	
	private final String AUTHORIZATION_URL = "http://localhost:8200/authorize";
	
	@Autowired
	private RemittanceService remittanceService;
	
	@Override
	public List<Remittance> parseUploadFile(MultipartFile file) throws IOException, NumberFormatException, ParseException{
		File file2 = new File(file.getOriginalFilename());
		FileOutputStream fos = new FileOutputStream(file2);
		fos.write(file.getBytes());
		fos.close();
		
		FileParser fileParser = new FileParser(file2);
		List<Remittance> list = fileParser.parseRemittance();			

		return list;
	}

	@Override	
	@EventListener(ApplicationReadyEvent.class)
	public void parseFileFromFolder() {
		// TODO Auto-generated method stub
		System.out.println("File watcher started...");
		try {
			WatchService watcher = FileSystems.getDefault().newWatchService();
			Path dir = Paths.get(FILEPATH);
			
			WatchKey key = dir.register(watcher, ENTRY_CREATE);
			
			while(true) {
				key = watcher.take();
				
				for(WatchEvent<?> event : key.pollEvents()) {
					WatchEvent.Kind<?> kind = event.kind();
					
					if(kind == OVERFLOW) {
						continue;
					}
					
					WatchEvent<Path> ev = (WatchEvent<Path>) event;
					Path filename = ev.context();
					
					try {	
						Path child = dir.resolve(filename);			
						System.out.println(filename.toString());
						System.out.println(Files.probeContentType(child));
						if(!Files.probeContentType(child).equals("text/plain")) {							
							System.out.println("File is not file type");
						}
												
						File file = child.toFile();						
						FileParser fileParser = new FileParser(file);
						List<Remittance> list = fileParser.parseRemittance();
						
						System.out.println("Valid file");
						//remittanceService.saveAll(list);
						for(Remittance remittance : list) {
							sendRemittanceJson(AUTHORIZATION_URL, remittance);
						}
						
					}catch(Exception e) {
						e.printStackTrace();
					}
										
				}
				
				boolean valid = key.reset();
				if(!valid) {
					break;
				}
			}			
		}catch(Exception e) {
			e.printStackTrace();
		}
								
	}
	
	@Override
	public void sendRemittanceJson(String url, Remittance remittance) {
		// TODO Auto-generated method stub
		RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);       
        HttpEntity<?> entity = new HttpEntity<Object>(remittance,headers);
        ResponseEntity<Object> responseEntity =  restTemplate.exchange(url, HttpMethod.POST, entity, Object.class);       
	}
}
