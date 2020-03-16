
public class Chat {

	public static void main(String[] args) {
		ChatModel model=new ChatModel();
		ChatView chat1=new ChatView(model);
		ChatView chat2=new ChatView(model);
		ChatController con1=new ChatController(model,chat1);
		ChatController con2=new ChatController(model, chat2);
	}

}
