package corsojava.archivio.menu;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class MenuIstance {
	private HashMap<String,ActionInterface> menu=new HashMap<String,ActionInterface>();
	private EngineInterface me;
	private String menuName;
	
	public MenuIstance(String menuName,EngineInterface me){
		this.menuName=menuName;
		this.me=me;
	}
	
	public void addMenuITem(String choice,ActionInterface ai){
		ai.setEngine(me);
		menu.put(choice,ai);
	}
	
	public ActionInterface getAction(String choice){
		return menu.get(choice);
	}
	
	public void showMenu(){
		System.out.println(menuName+"\n");
		Set<Entry<String,ActionInterface>> set=menu.entrySet();
		for(Entry<String,ActionInterface> ent:set){
			ActionInterface ai=ent.getValue();
			String choice=ent.getKey();
			System.out.println(choice+")"+" "+ai.getDescription());
		}
	}
	public String getName(){
		return menuName;
	}
}

