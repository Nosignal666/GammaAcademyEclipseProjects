package corsojava.Pattern;

public class Singleton {
	private static Singleton istance=null;
	private Singleton(){
		
	}
	public static Singleton istance(){
		if(istance==null) istance=new Singleton();
		return istance;
	}

}
/* in questo modo per ottenere l'unico oggetto istance bisogna invocare il metodo statico istance(), che restituirà 
sempre la stessa istanza */