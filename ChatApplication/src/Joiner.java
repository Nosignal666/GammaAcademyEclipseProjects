
public class Joiner extends Thread{

	private ChatView view;
	
	public Joiner(ChatView view) {
		super();
		this.view = view;
		view.joi=this;
		
	}
	@Override
	public synchronized void start() {
		try{
			String id=view.id;
			String host=view.host;
			while(id==null || host==null){
				Thread.sleep(1000);
				id=view.id;
				host=view.host;
				continue;
			}
			HttpDelegate.open(host).send("---- "+id+" JOINED ----\n");
		}catch(Exception e){
			view.setLogMessage("ERROR: "+e.getClass().getName()+": "+e.getMessage());
			System.out.println("ERROR: "+e.getClass().getName()+": "+e.getMessage());
			e.printStackTrace();
		}
	}

}
