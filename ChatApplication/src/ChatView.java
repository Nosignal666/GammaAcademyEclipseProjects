
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollBar;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.text.DefaultCaret;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JScrollPane;

public class ChatView {
	
    
	private JFrame frmChat;
	private JTextField submitField;
	public String id=null;
	private JTextArea chatArea;
	JScrollPane scrollPane;
	private int count=0;
	JScrollBar bar;
	
	
	private JFrame frmLogin;
	private JTextField idField;
	public String host=null;
	
	
	public Refresher ref;
	public Joiner joi;
	private JLabel lblNewLabel;
	private JTextField ipAdressField;
	Boolean firstTime=true;


	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	public void initialize() {
		
		
		frmLogin=new JFrame();
		frmLogin.getContentPane().setForeground(Color.BLUE);
		frmLogin.getContentPane().setBackground(Color.BLUE);
		frmLogin.setTitle("Login - Chat Borgo Delle Rondini");
		frmLogin.setBounds(100, 100, 558, 333);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		idField = new JTextField();
		idField.setForeground(Color.GREEN);
		idField.setFont(new Font("NSimSun", Font.BOLD, 22));
		idField.setBackground(Color.BLACK);
		idField.setBounds(187, 15, 177, 56);
		frmLogin.getContentPane().add(idField);
		idField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Nome Utente:");
		lblNewLabel_2.setForeground(Color.GREEN);
		lblNewLabel_2.setFont(new Font("NSimSun", Font.BOLD, 17));
		lblNewLabel_2.setBounds(46, 26, 129, 38);
		frmLogin.getContentPane().add(lblNewLabel_2);
		
		JButton entraButton = new JButton("Entra");
		entraButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(idField.getText().equals("")){
					idField.setText(" TYPE A NAME! ");
					return;
				}
				if(ipAdressField.getText().equals("")){
					ipAdressField.setText(" TYPE AN ADRESS! ");
					return;
				}
				id=idField.getText();
				host=ipAdressField.getText();
				frmLogin.setVisible(false);
				frmChat.setVisible(true);
			}
		});
		entraButton.setBackground(Color.BLACK);
		entraButton.setForeground(Color.GREEN);
		entraButton.setFont(new Font("NSimSun", Font.BOLD | Font.ITALIC, 20));
		entraButton.setBounds(197, 165, 155, 62);
		entraButton.setOpaque(true);
		frmLogin.getContentPane().add(entraButton);
		
		lblNewLabel = new JLabel("HostName:");
		lblNewLabel.setForeground(Color.GREEN);
		lblNewLabel.setFont(new Font("NSimSun", Font.BOLD, 17));
		lblNewLabel.setBounds(46, 102, 104, 40);
		frmLogin.getContentPane().add(lblNewLabel);
		
		ipAdressField = new JTextField();
		ipAdressField.setText("ACA-D8G-06");
		ipAdressField.setBackground(Color.BLACK);
		ipAdressField.setForeground(Color.GREEN);
		ipAdressField.setFont(new Font("NSimSun", Font.BOLD, 17));
		ipAdressField.setBounds(187, 103, 177, 38);
		frmLogin.getContentPane().add(ipAdressField);
		ipAdressField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Developed by Nosignal");
		lblNewLabel_1.setFont(new Font("Chicago", Font.ITALIC, 14));
		lblNewLabel_1.setForeground(Color.GREEN);
		lblNewLabel_1.setBounds(349, 257, 179, 16);
		frmLogin.getContentPane().add(lblNewLabel_1);
		
		
		
		frmChat = new JFrame();
		frmChat.getContentPane().setForeground(Color.YELLOW);
		frmChat.getContentPane().setBackground(Color.BLUE);
		frmChat.setForeground(Color.BLUE);
		frmChat.setBackground(Color.BLUE);
		frmChat.setTitle("Chat Borgo Delle Rondini");
		frmChat.setBounds(100, 100, 742, 550);
		frmChat.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmChat.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e){
				frmChat.setVisible(false);
				ref.terminate();
				try {
					ref.join();
					HttpDelegate.open(host).send("---- "+id+" LEFT ----\n");
				} catch (Exception e1) {
					System.out.println("ERROR: "+e1.getClass().getName()+": "+e1.getMessage());
					e1.printStackTrace();
					chatArea.setText("ERROR: "+e1.getClass().getName()+": "+e1.getMessage());
				}
				frmChat.dispose();
				System.exit(0);
			}
		});
		
		frmChat.getContentPane().setLayout(null);
		
		submitField = new JTextField();
		submitField.setText("type here, then press Enter to submit");
		submitField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==KeyEvent.VK_ENTER){
					if(submitField.getText().equals("")){
						submitField.setText("TYPE SOMETHING FIRST!");
						return;
					}
					try {
						HttpDelegate.open(host).send(id+": "+submitField.getText());
						submitField.setText("");
					} catch (Exception e) {
						System.out.println("ERROR: "+e.getClass().getName()+": "+e.getMessage());
						e.printStackTrace();
						chatArea.setText("ERROR: "+e.getClass().getName()+": "+e.getMessage());
					}
				}
					
			}
		});
		submitField.setFont(new Font("NSimSun", Font.BOLD, 15));
		submitField.setForeground(Color.GREEN);
		submitField.setBackground(Color.BLACK);
		submitField.setBounds(60, 445, 603, 34);
		frmChat.getContentPane().add(submitField);
		submitField.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(62, 26, 601, 406);
		frmChat.getContentPane().add(scrollPane);
		bar= scrollPane.getVerticalScrollBar();
		chatArea = new JTextArea();
		scrollPane.setViewportView(chatArea);
		chatArea.setEditable(false);
		chatArea.setFont(new Font("NSimSun", Font.BOLD, 17));
		chatArea.setBackground(Color.BLACK);
		chatArea.setForeground(Color.GREEN);
		chatArea.setLineWrap(true);
		DefaultCaret dc=(DefaultCaret)chatArea.getCaret();
		dc.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
		
		frmLogin.setVisible(true);
	}
	
	
	public void refresh(String messages){
		chatArea.setText(messages);
		int length=messages.length();
		if(count<length){
			chatArea.setCaretPosition(chatArea.getDocument().getLength());
	        count=length;
		}
		
		
		
	}
	
	public void setLogMessage(String logMessage){
		chatArea.setText(logMessage+"\n");
	}
}
