package corsojava.archivio.navigationActions;

import corsojava.archivio.menu.ActionInterface;
import corsojava.archivio.menu.MenuIstance;

public class NavigationAction extends AbstractNavigationAction implements ActionInterface {
	
	
	public NavigationAction(String description,MenuIstance nextMenu){
		super(description,nextMenu);
		
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public boolean performAction() {		
		engine.setCurrentMenu(nextMenu);
		return true;
	}

}
