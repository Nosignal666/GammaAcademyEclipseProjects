import java.util.Vector;

public class ChatModel implements Model {
	
	Vector<String> messages=new Vector<String>();
	

	@Override
	public void addMessage(String message) {
		if(messages.size()>100){
			messages.clear();
			messages.add("AUTOMATIC CHAT CLEANING DONE.."+"\n");
		}
		
		messages.add(message);
	}

	@Override
	public String getMessages() {
		String allMessages="";
		for(String message:messages){
			allMessages+=message+"\n";
		}
		return allMessages;
	}
	
	
	

}
