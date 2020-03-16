import java.util.Vector;

public class ChatModel implements ChatObservable{
	
	private Vector<String> messages=new Vector<String>();
	private Vector<ChatObserver> forumObservers=new Vector<ChatObserver>();

	@Override
	public void attach(ChatObserver forumObserver) {
		forumObservers.add(forumObserver);
	}

	@Override
	public void detach(ChatObserver forumObserver) {
		forumObservers.remove(forumObserver);
	}

	@Override
	public void inform() {
		for(ChatObserver co:forumObservers){
			co.update();
		}
	}
	
	public int getId(){
		return forumObservers.size();
	}
	
	public void addMessage(String message){
		messages.add(message);
		inform();
		
	}

	public String getMessages(){
		int size=messages.size();
		String allMessages="";
		for(int i=0;i<size;i++){
			allMessages+=messages.get(i)+"\n";
		}
		return allMessages;
		
	}
}
