package corsojava.archivio.menu;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class MenuEngine implements EngineInterface{
	private HashMap<String,MenuIstance> menuSet=new HashMap<String,MenuIstance>();
	private String startMenu;
	private MenuIstance currentMenu;
	
	public MenuEngine(String startMenu){
		this.startMenu=startMenu;
	}
	
	public void addMenu(MenuIstance mi){
		menuSet.put(mi.getName(), mi);
	}
	
	public void setCurrentMenu(MenuIstance mi) {
		currentMenu=mi;
	}
	
	public void startEngine() throws MenuException{
		boolean running=true;
		InputStreamReader isr=new InputStreamReader(System.in);
		BufferedReader br=new BufferedReader(isr);
		currentMenu=menuSet.get(startMenu);
		
			try{
				while(running){
				if(currentMenu==null) throw new MenuException("null menu");
				currentMenu.showMenu();
				System.out.print("\n"+"choice>>");
				String choice=br.readLine();
				ActionInterface ai=currentMenu.getAction(choice);
				if(ai==null){
					System.out.println("comando non riconosciuto");
					continue;
				}
				running=ai.performAction();
			    }
			}catch(Exception e){
				throw new MenuException("Menu Error: "+e.getMessage());
			}
		}
	}

