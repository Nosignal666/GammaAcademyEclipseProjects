package corsojava.storage;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class WriterThread extends Thread {
	private long timeout=1000;
	public DataInterface dataInter;
	public LinkedBlockingQueue<DataInterface> lbq=new LinkedBlockingQueue<DataInterface>();
	public StorageManager sm;
	boolean shouldRun;
	
	public WriterThread(String name, LinkedBlockingQueue<DataInterface> lbq, StorageManager sm){
		super(name);
		this.sm=sm;
		this.lbq=lbq;
	}
	
    public void turnOff(){
    	shouldRun=false;
    }
	
	public void run() {
		shouldRun=true;
		while(shouldRun){
			try{
				if(!((dataInter=lbq.poll(timeout,TimeUnit.MILLISECONDS))==null)){
					sm.writeData(dataInter);
				}
			}catch(Exception e){
				System.out.println("WRITE ERROR: "+e.getMessage());
			}
		}
	}
}



