package corsojava.storage;

import java.util.HashMap;

public class DataElement {
	private String nomeclasse;
	private HashMap<String,String> data= new HashMap<String,String>();
	public DataElement() {
		super();
	}
	public String getNomeclasse() {
		return nomeclasse;
	}
	public void setNomeclasse(String nomeclasse) {
		this.nomeclasse = nomeclasse;
	}
	public HashMap<String, String> getData() {
		return data;
	}
	public void setData(HashMap<String, String> data) {
		this.data = data;
	}
}