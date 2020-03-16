
public class ChatController implements ChatObserver {
	
	private ChatView chatView;
	private ChatModel model;
	private int messageNumber=0;
	
	public ChatController(ChatModel model,ChatView chatView){
		this.chatView=chatView;
		this.model=model;
		chatView.attach(this);
	}

	@Override
	public void update() {
		messageNumber++;
		System.out.println(messageNumber);
	}
	
	public void processSubmitAction(String message){
		model.addMessage(message);
	}

}
