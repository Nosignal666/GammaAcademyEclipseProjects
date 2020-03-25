package com.anagrafica.anagraficaService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.StringTokenizer;

import javax.servlet.ServletException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import corsojava.dbstorage.DatabaseStorageManager;
import corsojava.storage.StorageManager;

@Configuration
public class AppConfig {
	
	@Bean
	DatabaseStorageManager getStorage() throws ServiceException{
		/*
		StorageManager sm=new StorageManager();
		
		FileInputStream fis;
		BufferedReader br;
		InputStreamReader isr;
		String line;
		HashMap<String,String> configData=new HashMap<String,String>();
		try {
			fis = new FileInputStream(new File("C:\\anagraficaServiceConf\\config.txt"));
			isr=new InputStreamReader(fis);
			br=new BufferedReader(isr);
			
			while(!((line=br.readLine())==null)){
				if(line.startsWith("#")) continue;
				StringTokenizer st=new StringTokenizer(line,"=");
				configData.put(st.nextToken(), st.nextToken());
		    } 
			sm.init(configData.get("support"), new URL("file:\\"+configData.get("storageParameter")));
			br.close();
		}catch (Exception e) {
			throw new ServiceException("CONFIG ERROR: "+e.getMessage());
		}
	return sm;	
	*/
	
	
		DatabaseStorageManager db=new DatabaseStorageManager();
		try{
			db.initStorage();
		}catch(Exception e){
			throw new ServiceException("fail to initialize db: "+e.getMessage());
		}
		
		return db;
	}
}
