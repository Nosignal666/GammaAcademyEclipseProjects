package corsojava.archivio.menu;


public interface ActionInterface {
	public boolean performAction() throws MenuException;
	public String getDescription();
	public void  setEngine(EngineInterface engine);

}
