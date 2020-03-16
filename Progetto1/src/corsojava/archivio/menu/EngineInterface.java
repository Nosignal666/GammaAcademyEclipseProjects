package corsojava.archivio.menu;

public interface EngineInterface {
	public void setCurrentMenu(MenuIstance mi);
	public void addMenu(MenuIstance mi);
	public void startEngine() throws MenuException;
}
