package gui;

import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JTable;
import javax.swing.table.TableModel;

import storage.StorageException;
import storage.StorageManager;
import strutturaDati.Azienda;
import strutturaDati.Entry;

public class Controller {
	
	StorageManager sm;
	MastroView mastroView;

	public Controller() throws MalformedURLException, StorageException {
		super();
		sm=new StorageManager();
		sm.initStorage("storage.FileStorageManager",new URL("file:\\C:\\Archivio"));
		mastroView=new MastroView(this);
		mastroView.createView();
	}
	public TableModel getTableModel(String idAzienda) throws StorageException, SecurityException, ClassNotFoundException{
		Azienda az=sm.readAzienda(idAzienda);
		MastroModel mm=new MastroModel();
		mm.setEntryList(az.entryList);
		return mm;
	}
	
	public void insertFromTable(String idAzienda, JTable entryMastro)throws StorageException{
		Azienda az=new Azienda(idAzienda);
		MastroModel mm=(MastroModel)entryMastro.getModel();
		az.setEntryList(mm.entryList);
		sm.writeAzienda(az);
		mm.clear();
		
	}
	
	@Deprecated
	public String[][] getMastro(String idAzienda) throws StorageException, SecurityException, ClassNotFoundException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
		Azienda az=sm.readAzienda(idAzienda);
		Field[] fields=Class.forName("strutturaDati.Entry").getDeclaredFields();
		ArrayList<Entry> entryList=az.entryList;
		
		String[][] mastro=new String[entryList.size()][fields.length];
		for(int i=0; i<entryList.size();i++){
			for(int j=0; j<fields.length;j++){
				Field f=fields[j];
				f.setAccessible(true);
				mastro[i][j]=(String)f.get(entryList.get(i));
			}
		}
		return mastro;
	}
	@Deprecated
	public String[] getEntryFields() throws SecurityException, ClassNotFoundException{
		Field[] fields=Class.forName("strutturaDati.Entry").getDeclaredFields();
		String[] fieldNames=new String[fields.length];
		for(int i=0; i<fields.length;i++){
			fieldNames[i]=fields[i].getName();
		}
		return fieldNames;
		
	}

}
