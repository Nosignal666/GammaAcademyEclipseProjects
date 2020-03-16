package corsojava.archivio.actions;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Set;
import java.util.Map.Entry;

import corsojava.archivio.menu.MenuException;
import corsojava.storage.DataElement;
import corsojava.storage.DataInterface;
import corsojava.storage.StorageManager;

public class CercaAction extends AbstractAction {
	
	StorageManager sm;
	
	public CercaAction(String description,StorageManager sm){
		super(description);
		this.sm=sm;
	}

	@Override
	public boolean performAction() throws MenuException{
		InputStreamReader isr=new InputStreamReader(System.in);
		BufferedReader br=new BufferedReader(isr);
		DataInterface dataInter;
		DataElement de;
		String type=null;
		
		try{
			switch(description){
			case "Studente":
				System.out.print("nome-cognome-matricola>>");
				type="DatiStudente";
				break;
			case "Utente":
				System.out.print("nome-cognome-materia>>");
				type="DatiUtente";
				break;
			case "Azienda":
				System.out.print("nome-PIva>>");
				type="DatiAzienda";
				break;
				
			}
			dataInter=sm.readData(type+"-"+br.readLine());
			de=dataInter.getDataElement();
			if(!de.getNomeclasse().equals("corsojava.anagrafica.Dati"+description)) throw new FileNotFoundException("il file richiesto non è "+description);
			HashMap<String,String> hm=de.getData();
			Set<Entry<String,String>> set=hm.entrySet();
			for(Entry<String,String> ent:set){
				System.out.println(ent.getKey()+"="+ent.getValue());
			}
		}catch(Exception e){
			System.out.println("SearchAction failed: "+e.getMessage());
		}
		
		return true;
	}

}
