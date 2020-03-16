package corsojava.archivio.navigationActions;

import corsojava.archivio.menu.MenuIstance;

public class ExitAction extends AbstractNavigationAction {
	
	public ExitAction(String description,MenuIstance nextMenu){
		super(description,null);
	}

	@Override
	public boolean performAction() {
		return false;
	}

}
