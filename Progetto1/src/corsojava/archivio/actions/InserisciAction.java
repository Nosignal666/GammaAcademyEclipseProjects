package corsojava.archivio.actions;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.concurrent.LinkedBlockingQueue;
import corsojava.archivio.menu.ActionInterface;
import corsojava.storage.DataInterface;

public class InserisciAction extends AbstractAction implements ActionInterface{
	
	public LinkedBlockingQueue<DataInterface> lbq;
	
	public InserisciAction(String desc,LinkedBlockingQueue<DataInterface> lbq){
		super(desc);
		this.lbq=lbq;
	}

	@Override
	public boolean performAction(){
		try{
			Class<?> clazz=Class.forName("corsojava.anagrafica.Dati"+description);
			DataInterface dataInter=(DataInterface)clazz.newInstance();
			
			InputStreamReader isr=new InputStreamReader(System.in);
			BufferedReader br=new BufferedReader(isr);
			
			Field[] fields;
			
			while(!clazz.getName().contentEquals("java.lang.Object")){
				fields=clazz.getDeclaredFields();
				for(Field f:fields){
					f.setAccessible(true);
					System.out.print(f.getName()+" >>");
					f.set(dataInter, br.readLine());
				}
				clazz=clazz.getSuperclass();
			}
			lbq.put(dataInter);
		}catch(Exception e){
			System.out.println("InsertAction failed: "+e.getMessage());
		}
		return true;
	}


}
