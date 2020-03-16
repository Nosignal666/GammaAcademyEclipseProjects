package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import storage.StorageException;
import strutturaDati.Azienda;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.border.EmptyBorder;
import java.awt.Font;

public class MastroView {
	
	
    Controller mastroController;
	private JFrame frame;
	private JTextField idAziendaField;
	private JTextArea messaggi;
	JTextArea messaggiInsert;
	private JScrollPane scrollPane;
	private JTable tabellaMastro;
	private JTextField idAziendaInsert;
	private JTable entryMastro;


	/**
	 * Create the application.
	 */
	public MastroView(Controller mastroController) {
		this.mastroController=mastroController;
	}

	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	public void createView() {
		frame = new JFrame();
		
		frame.setBounds(100, 100, 788, 602);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNomeaziendacodfiscale = new JLabel("Nome Azienda:");
		lblNomeaziendacodfiscale.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNomeaziendacodfiscale.setBounds(12, 11, 148, 16);
		
		JButton btnNewButton = new JButton("Cerca Mastro");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String idAzienda=idAziendaField.getText();
				try {
					tabellaMastro.setModel(mastroController.getTableModel(idAzienda));
					messaggi.setText("Hai caricato il mastro di "+idAzienda);
				} catch (Exception e) {
					messaggi.setText("Mastro non trovato: "+e.getClass().getName()+": "+e.getMessage());
				}
				
			}
		});
		btnNewButton.setBounds(95, 77, 109, 25);
		
		messaggi = new JTextArea();
		messaggi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		messaggi.setLineWrap(true);
		messaggi.setBounds(420, 10, 229, 92);
		
		
		
		tabellaMastro = new JTable();
		tabellaMastro.setFont(new Font("Tahoma", Font.PLAIN, 17));
		tabellaMastro.setFillsViewportHeight(true);
		tabellaMastro.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));
		tabellaMastro.setBorder(null);
		
		scrollPane = new JScrollPane(tabellaMastro);
		scrollPane.setViewportBorder(null);
		scrollPane.setBounds(32, 137, 703, 372);
	
		
		JLabel labelMessaggi = new JLabel("Messaggi: ");
		labelMessaggi.setFont(new Font("Tahoma", Font.PLAIN, 17));
		labelMessaggi.setVerticalAlignment(SwingConstants.BOTTOM);
		labelMessaggi.setBounds(328, 7, 80, 21);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 780, 564);
		frame.getContentPane().add(tabbedPane);
		
		
		
		JPanel RicercaPanel = new JPanel();
		tabbedPane.addTab("Ricerca", null, RicercaPanel, null);
		RicercaPanel.setLayout(null);
		RicercaPanel.add(btnNewButton);
		RicercaPanel.add(labelMessaggi);
		RicercaPanel.add(lblNomeaziendacodfiscale);
		RicercaPanel.add(scrollPane);
		RicercaPanel.add(messaggi);
		
		idAziendaField = new JTextField();
		idAziendaField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		idAziendaField.setBounds(139, 8, 177, 33);
		idAziendaField.setColumns(10);
		RicercaPanel.add(idAziendaField);
		
		JPanel InserimentoPanel = new JPanel();
		tabbedPane.addTab("Inserimento", null, InserimentoPanel, null);
		InserimentoPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("NomeAzienda:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(12, 13, 117, 29);
		InserimentoPanel.add(lblNewLabel);
		
		idAziendaInsert = new JTextField();
		idAziendaInsert.setFont(new Font("Tahoma", Font.PLAIN, 17));
		idAziendaInsert.setText("");
		idAziendaInsert.setBounds(128, 15, 180, 45);
		InserimentoPanel.add(idAziendaInsert);
		idAziendaInsert.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Inserisci");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				MastroModel model=(MastroModel)entryMastro.getModel();
				try {
					mastroController.insertFromTable(idAziendaInsert.getText(),entryMastro);
					messaggiInsert.setText("Operazione Effettuata");
				} catch (StorageException e) {
					messaggiInsert.setText("Mastro non aggiornato: "+e.getClass().getName()+": "+e.getMessage());
				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton_1.setBounds(240, 179, 160, 40);
		InserimentoPanel.add(btnNewButton_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportBorder(null);
		scrollPane_1.setBounds(44, 91, 678, 61);
		InserimentoPanel.add(scrollPane_1);
		
		entryMastro = new JTable();
		scrollPane_1.setViewportView(entryMastro);
		entryMastro.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		entryMastro.setModel(new MastroModel());
		entryMastro.getTableHeader().setFont(new Font("Arial", Font.BOLD, 19));
		entryMastro.setRowHeight(30);
		InserimentoPanel.add(entryMastro.getTableHeader());
		
		JLabel lblNewLabel_1 = new JLabel("Messaggi:");
		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(208, 243, 70, 29);
		InserimentoPanel.add(lblNewLabel_1);
		
		messaggiInsert = new JTextArea();
		messaggiInsert.setFont(new Font("Tahoma", Font.PLAIN, 15));
		messaggiInsert.setLineWrap(true);
		messaggiInsert.setBounds(290, 249, 262, 81);
		InserimentoPanel.add(messaggiInsert);
		
		
		
		
		
		
		frame.setVisible(true);
	}
}
