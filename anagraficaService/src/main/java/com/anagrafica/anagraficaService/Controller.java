package com.anagrafica.anagraficaService;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import corsojava.dbstorage.DatabaseStorageManager;
import corsojava.storage.DataElement;
import corsojava.storage.DataInterface;
import corsojava.storage.StorageManager;

@RestController
@CrossOrigin()
public class Controller {
	
	@Autowired
	@Qualifier("getStorage")
	private DatabaseStorageManager sm;
	
	@PostMapping("/inserimento")
	void add(@RequestBody String body) throws ServiceException{
		ObjectMapper objectMapper = new ObjectMapper();
		try{
		    HashMap<String,String> bodyMap= objectMapper.readValue(body,new TypeReference<HashMap<String,String>>(){});
			DataElement de=new DataElement();
			DataInterface di=(DataInterface)Class.forName(bodyMap.get("type")).newInstance();
			de.setData(bodyMap);
			di.setDataElement(de);
			sm.writeData(di);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("ERROR: "+ e.getClass().getName()+": "+e.getMessage());
		}
	}
	
	@GetMapping("/ricerca/{index}")
	ResponseEntity<String> get(@PathVariable String index){
		ObjectMapper mapper=new ObjectMapper();
		String jsonString;
		DataInterface di;
		try{
			di=sm.readData(index);
			jsonString=mapper.writeValueAsString(di);
		}catch(Exception e){
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>(jsonString,HttpStatus.OK);
	}
	
	

}
