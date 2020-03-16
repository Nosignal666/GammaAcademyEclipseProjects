package corsojava.archivio;


import java.net.URL;

import java.util.concurrent.LinkedBlockingQueue;

import corsojava.archivio.actions.CercaAction;
import corsojava.archivio.actions.InserisciAction;
import corsojava.archivio.navigationActions.ExitAction;
import corsojava.archivio.navigationActions.NavigationAction;
import corsojava.storage.DataInterface;
import corsojava.storage.StorageManager;
import corsojava.storage.WriterThread;
import corsojava.archivio.menu.EngineInterface;
import corsojava.archivio.menu.MenuEngine;
import corsojava.archivio.menu.MenuIstance;

public class Archivio {
	
	LinkedBlockingQueue<DataInterface> lbq=new LinkedBlockingQueue<DataInterface>();
	StorageManager sm=new StorageManager();
	WriterThread wt=new WriterThread("writerThread",lbq,sm);
	URL dir;
	
	public Archivio(URL dir){
		this.dir=dir;
	}
	public Archivio(){
		
	}
	
	EngineInterface engine=new MenuEngine("---Menu Principale---");
	MenuIstance topMenu=new MenuIstance("---Menu Principale---", engine);
	MenuIstance inserimento=new MenuIstance("---Menu Inserimento---",engine);
	MenuIstance ricerca=new MenuIstance("--Menu Ricerca---",engine);
	
	public void initResource(){
		try {
			sm.init("corsojava.filestorage.FileStorageManager",new URL("file:\\C:\\Users\\Gamma.Academy\\Desktop\\Archivio"));
			wt.start();
		}catch(Exception e){
			System.out.println("Resource Initialization failed: " +e.getMessage());
		}
	}
	public void initEngine(){
		topMenu.addMenuITem("1",new NavigationAction("inserimento",inserimento));
		topMenu.addMenuITem("2", new NavigationAction("ricerca", ricerca));
		topMenu.addMenuITem("3", new ExitAction("uscita",null));
		
		inserimento.addMenuITem("1", new InserisciAction("Studente",lbq));
		inserimento.addMenuITem("2", new InserisciAction("Utente",lbq));
		inserimento.addMenuITem("3", new InserisciAction("Azienda",lbq));
		inserimento.addMenuITem("4", new NavigationAction("indietro",topMenu));
		
		ricerca.addMenuITem("1", new CercaAction("Studente",sm));
		ricerca.addMenuITem("2", new CercaAction("Utente",sm));
		ricerca.addMenuITem("3", new CercaAction("Azienda",sm));
		ricerca.addMenuITem("4", new NavigationAction("indietro",topMenu));
		
		
		engine.addMenu(topMenu);
		engine.addMenu(inserimento);
		engine.addMenu(ricerca);
	}
	public void start(){
		try{
			engine.startEngine();
			wt.turnOff();
			wt.join();
		}catch(Exception e){
			System.out.println("an error occure while starting the engine: "+e.getMessage());
		}
		
		
		
		
	}
	
	
	public static void main(String args[]){
		
		Archivio arch=new Archivio();
		arch.initResource();
		arch.initEngine();
		arch.start();
		
    }
}
