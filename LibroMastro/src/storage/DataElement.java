package storage;

import java.util.ArrayList;
import java.util.HashMap;

public class DataElement {
	private String nomeAzienda;
	private ArrayList<HashMap<String,String>> data= new ArrayList<HashMap<String,String>>();
	public DataElement() {
		super();
	}
	public String getNomeAzienda() {
		return nomeAzienda;
	}
	public void setNomeAzienda(String nomeclasse) {
		this.nomeAzienda = nomeclasse;
	}
	public ArrayList<HashMap<String, String>> getData() {
		return data;
	}
	public void setData(ArrayList<HashMap<String, String>> data) {
		this.data = data;
	}
}