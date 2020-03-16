package corsojava.anagrafica;

import java.lang.reflect.Field;
import java.util.HashMap;

import corsojava.storage.DataElement;
import corsojava.storage.DataInterface;
import corsojava.storage.IllegaIndexException;

public class CredenzialiUtente implements DataInterface {
	private String username;
	private String password;
	
	public void setDataElement(DataElement dataElement) throws IllegalArgumentException, IllegalAccessException{
		Class<?> clazz=this.getClass();
		Field[] fields;
		
		while(!clazz.getName().equals("java.lang.Object")){
			fields=clazz.getDeclaredFields();
			for(Field f:fields){
				f.setAccessible(true);
				f.set(this,dataElement.getData().get(f.getName()));
		    }
			clazz=clazz.getSuperclass();
		}
	}
	
	public DataElement getDataElement() throws IllegalArgumentException, IllegalAccessException{
		
		DataElement de=new DataElement();
		HashMap<String,String> map=new HashMap<String,String>();
		de.setNomeclasse(this.getClass().getName());
		
		Class<?> clazz=this.getClass();
		Field[] fields;

		while(!clazz.getName().contentEquals("java.lang.Object")){
			fields=clazz.getDeclaredFields();
			for(Field f:fields){
				f.setAccessible(true);
				map.put(f.getName(),(String)f.get(this));
		    }
			clazz=clazz.getSuperclass();
		}
		de.setData(map);
		return de;
	}

	
	@Override
	public String getIndex() throws IllegaIndexException {
		throw new IllegaIndexException();
	}
	
	public CredenzialiUtente(){
	};
	public CredenzialiUtente(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
