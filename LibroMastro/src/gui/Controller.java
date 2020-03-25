package gui;

import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import storage.StorageException;
import storage.StorageManager;
import strutturaDati.Azienda;

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

}
