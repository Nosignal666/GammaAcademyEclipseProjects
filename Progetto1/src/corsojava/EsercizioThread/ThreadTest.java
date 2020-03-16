package corsojava.EsercizioThread;

public class ThreadTest extends Thread {
	private int interval;
	public ThreadTest(String name, int interval){
		super(name);
		this.interval=interval;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try{
			Thread.sleep(3000);
			for(int i=1; i<20; i++){
				System.out.println(getName()+" "+i);
				Thread.sleep(interval);
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	

}
