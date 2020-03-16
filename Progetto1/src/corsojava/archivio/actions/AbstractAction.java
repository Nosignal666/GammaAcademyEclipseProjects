package corsojava.archivio.actions;

import corsojava.archivio.menu.ActionInterface;
import corsojava.archivio.menu.EngineInterface;



public abstract class AbstractAction implements ActionInterface{
	protected String description;
	protected EngineInterface engine;
	
	public AbstractAction(String description){
		this.description=description;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setEngine(EngineInterface engine) {
		this.engine=engine;
		
	}
	
	

}
