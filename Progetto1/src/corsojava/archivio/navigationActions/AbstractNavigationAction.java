package corsojava.archivio.navigationActions;

import corsojava.archivio.actions.AbstractAction;
import corsojava.archivio.menu.MenuIstance;

public abstract class AbstractNavigationAction extends AbstractAction {
	
	protected MenuIstance nextMenu;
	
	public AbstractNavigationAction(String description,MenuIstance nextMenu){
		super(description);
		this.nextMenu=nextMenu;
	}

	@Override
	public String getDescription() {
		return description;
	}

}
