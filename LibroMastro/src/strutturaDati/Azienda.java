package strutturaDati;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import storage.DataElement;

public class Azienda {
	private String nome;
	public ArrayList<Entry> entryList;
	public Azienda(String nome, ArrayList<Entry> entryList) {
		super();
		this.nome = nome;
		this.entryList = entryList;
	}
	
	public Azienda(String nome) {
		super();
		this.nome = nome;
	}

	public void addEntry(Entry entry){
		entryList.add(entry);
	}
	public void setEntryList(ArrayList<Entry> entryList) {
		this.entryList = entryList;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
