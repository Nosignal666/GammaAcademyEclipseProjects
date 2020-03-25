package strutturaDati;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import storage.DataElement;

public class Azienda {
	private String nomeAzienda;
	private int[] partitaIva;
	
	
	public Azienda(String nomeAzienda,int[] partitaIva, ArrayList<Entry> entryList){
		this.nomeAzienda = nomeAzienda;
		this.entryList = entryList;
		this.partitaIva=partitaIva;
	}
	
	public Azienda(String nomeAzienda,int[] partitaIva) {
		this.nomeAzienda = nomeAzienda;
		this.partitaIva=partitaIva;
	}

	public void addEntry(Entry entry){
		entryList.add(entry);
	}
	public void setEntryList(ArrayList<Entry> entryList) {
		this.entryList = entryList;
	}
	public String getNome() {
		return nomeAzienda;
	}
	public int[] getPartitaIva(){
		return this.partitaIva;
	}
	
}
