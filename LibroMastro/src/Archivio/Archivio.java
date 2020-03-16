package Archivio;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import gui.Controller;
import storage.StorageException;
import storage.StorageManager;
import strutturaDati.Azienda;
import strutturaDati.Entry;

public class Archivio {

	public static void main(String[] args) throws MalformedURLException, StorageException {
		
		/*
		Azienda az=new Azienda("HotelMiramonti","12345");
		ArrayList<Entry> entryList=new ArrayList<Entry>();
		entryList.add(new Entry("10/10/10", "1029", "10"));
		az.setEntryList(entryList);
		StorageManager sm=new StorageManager();
		sm.initStorage("storage.FileStorageManager", new URL("file:\\C:\\Users\\Gamma.Academy\\Desktop\\Archivio"));
		sm.writeAzienda(az);
		Azienda az2=sm.readAzienda("HotelMiramonti-12345");
		StorageManager sm2= new StorageManager();
		sm2.initStorage("storage.FileStorageManager", new URL("file:\\C:\\Users\\Gamma.Academy\\Desktop\\Archivio2"));
		sm2.writeAzienda(az2);
		*/
		
		
		Controller mastroController=new Controller();
	

	}

}
