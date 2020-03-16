package corsojava.Pattern;

public class SingletonInizializzato {
	final private static SingletonInizializzato istance =new SingletonInizializzato();
	private SingletonInizializzato(){
		
	}
	public static SingletonInizializzato istance(){
		return istance;
	}
}
/* questo è anche thread safe*/