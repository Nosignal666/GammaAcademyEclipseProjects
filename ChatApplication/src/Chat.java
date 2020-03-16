
public class Chat {

	public static void main(String[] args) {
		
		ChatView view=new ChatView();
		Refresher ref=new Refresher(view);
		Joiner joi=new Joiner(view);
		view.initialize();
		joi.start();
		ref.start();
	}

}
