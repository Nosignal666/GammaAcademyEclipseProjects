import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class ChatView implements ChatObserver,ChatObservable{

	private JFrame frmChat;
	private JTextArea textArea;
	private JButton btnNewButton;
	private Vector<ChatObserver> chatObserversVector=new Vector<ChatObserver>();
	private ChatModel model;
	private int id;
	
	
	
	ActionListener submitHandler=new ActionListener(){
		public void actionPerformed(ActionEvent e){
			inform();
			ChatController con;
			for(int i=0;i<chatObserversVector.size();i++){
				con=(ChatController)chatObserversVector.get(i);
				con.processSubmitAction("Client "+id+": "+textField.getText());
			}
		}
	};
	private JTextField textField;

	
	public ChatView(ChatModel model) {
		this.model=model;
		model.attach(this);
		id=model.getId();
		initialize();
	}


	private void initialize() {
		frmChat = new JFrame();
		frmChat.setTitle("Chat");
		frmChat.setBounds(100, 100, 711, 520);
		frmChat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmChat.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(154, 13, 286, 43);
		frmChat.getContentPane().add(textField);
		textField.setColumns(10);
		
		textArea = new JTextArea();
		textArea.setBounds(60, 79, 571, 340);
		frmChat.getContentPane().add(textArea);
		
		btnNewButton = new JButton("Inserisci");
		btnNewButton.addActionListener(submitHandler);
		btnNewButton.setBounds(485, 22, 97, 25);
		frmChat.getContentPane().add(btnNewButton);
		frmChat.setVisible(true);
	}


	@Override
	public void attach(ChatObserver forumObserver) {
		chatObserversVector.add(forumObserver);
	}


	@Override
	public void detach(ChatObserver forumObserver) {
		chatObserversVector.remove(forumObserver);
	}


	@Override
	public void inform() {
		for(ChatObserver co: chatObserversVector){
			co.update();
		}
	}


	@Override
	public void update() {
		textArea.setText(model.getMessages());
	
	}
}
