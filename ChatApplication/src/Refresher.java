import javax.swing.SwingUtilities;

public class Refresher extends Thread {
	private ChatView view;
	private Boolean isRunning=true;

	public Refresher(ChatView view) {
		super();
		this.view = view;
		view.ref=this;
	}
	
	public void terminate(){
		isRunning=false;
	}

	@Override
	public void run() {
		String host=view.host;
		
			try {
				while(host==null){
					Thread.sleep(2000);
					host=view.host;
					continue;
				}
			} catch (InterruptedException e) {
				view.setLogMessage("ERROR: "+e.getClass().getName()+": "+e.getMessage());
				System.out.println("ERROR: "+e.getClass().getName()+": "+e.getMessage());
				e.printStackTrace();
			}
			try {
				while(isRunning){
				String messages=HttpDelegate.open(host).receive();
				SwingUtilities.invokeLater(new Runnable(){
					@Override
					public void run() {
						view.refresh(messages);
					}
				});
				Thread.sleep(2000);
				}
			} catch (Exception e) {
				view.setLogMessage("ERROR: "+e.getClass().getName()+": "+e.getMessage());
				System.out.println("ERROR: "+e.getClass().getName()+": "+e.getMessage());
				e.printStackTrace();
			}
	}
}


