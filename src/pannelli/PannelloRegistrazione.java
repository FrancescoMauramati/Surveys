package pannelli;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import classi.GestioneDatabase;
import classi.Utente;

import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JScrollPane;

public class PannelloRegistrazione extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField campoNome;
	private JTextField campoCognome;
	private JTextField campoUsername;
	private JPasswordField campoPassword;
	
	private JLabel lblNome;
	private JLabel lblCognome;
	private JLabel lblUsername;
	private JLabel lblPassword;
	
	private JButton btnIndietro;
	private JButton btnRegistrazione;
	
	public void bottoneIndietro(String tipo) {
		btnIndietro.addActionListener(new ActionListener() {
	           @Override
	           public void actionPerformed(ActionEvent e) {
	        	   if(tipo.equals("utente"))
	        		   ((Frame) SwingUtilities.getWindowAncestor(PannelloRegistrazione.this)).switchToLoginUtente();
	        	   else
	        		   ((Frame) SwingUtilities.getWindowAncestor(PannelloRegistrazione.this)).switchToLoginAdmin();
	        	    campoNome.setText(null);
	       			campoCognome.setText(null);
	       			campoUsername.setText(null);
	       			campoPassword.setText(null);
	           }
	       });
	}
	
	public void bottoneRegistrazione(String tipo) {
		btnRegistrazione.addActionListener(new ActionListener() {
	           @Override
	           public void actionPerformed(ActionEvent e) {
	        	   	String nome = campoNome.getText();
	        	   	String cognome = campoCognome.getText();
	        	    String username = campoUsername.getText();
		       		char password[] = campoPassword.getPassword();

		       		if(nome.equals("")){
		       			JOptionPane.showMessageDialog(PannelloRegistrazione.this,
		       				    "Il nome non può essere vuoto!",
		       				    "Errore nella registrazione",
		       				    JOptionPane.ERROR_MESSAGE);
		       		}
		       		 if(cognome.equals("")){
		       			JOptionPane.showMessageDialog(PannelloRegistrazione.this,
		       				    "Il cognome non può essere vuoto!",
		       				    "Errore nella registrazione",
		       				    JOptionPane.ERROR_MESSAGE);
		       		}
		       		 if(username.equals("")){
		       			JOptionPane.showMessageDialog(PannelloRegistrazione.this,
		       				    "Il nome utente non può essere vuoto!",
		       				    "Errore nella registrazione",
		       				    JOptionPane.ERROR_MESSAGE);
		       		}
		       		 if(password.length<6){
		       			JOptionPane.showMessageDialog(PannelloRegistrazione.this,
		       				    "La password deve avere almeno 6 caratteri!",
		       				    "Errore nella registrazione",
		       				    JOptionPane.ERROR_MESSAGE);
		       			campoPassword.setText(null);
		       		}
		       		if(!nome.equals("") && !cognome.equals("") && !username.equals("") && password.length>=6) { //se è tutto giusto
			       		Utente utente = new Utente(nome, cognome, username, password);
			       		System.out.println(utente);
			       		GestioneDatabase r = new GestioneDatabase();
			       		boolean check=false;
			       		
			       		try {
							check=r.registraUtente(utente, tipo);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			       		
			       		if(check) {
			       			JOptionPane.showMessageDialog(PannelloRegistrazione.this,
			       				    "Account "+tipo+" creato con successo!\nSi prega di fare l'accesso.");
			       			((Frame) SwingUtilities.getWindowAncestor(PannelloRegistrazione.this)).switchToSchermataPrincipale();
			       			campoNome.setText(null);
			       			campoCognome.setText(null);
			       			campoUsername.setText(null);
			       			campoPassword.setText(null);
			       		}
			       		
			       		else {
			       			JOptionPane.showMessageDialog(PannelloRegistrazione.this,
			       				    "L'username " + username + " è già in uso!",
			       				    "Errore nella registrazione",
			       				    JOptionPane.ERROR_MESSAGE);
			       			campoUsername.setText(null);
			       		}
		       		}
		       		
	           }
	       });
	}

	/**
	 * Create the panel.
	 */
	public PannelloRegistrazione(String tipo) {
		setLayout(null);
		
		lblNome = new JLabel("NOME");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNome.setBounds(106, 68, 44, 14);
		add(lblNome);
		
		lblCognome = new JLabel("COGNOME");
		lblCognome.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCognome.setBounds(90, 134, 77, 14);
		add(lblCognome);
		
		lblUsername = new JLabel("NOME UTENTE");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUsername.setBounds(74, 200, 109, 14);
		add(lblUsername);
		
		lblPassword = new JLabel("PASSWORD");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(86, 266, 85, 14);
		add(lblPassword);
		
		btnIndietro = new JButton("INDIETRO");
		btnIndietro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnIndietro.setBounds(10, 11, 101, 35);
		bottoneIndietro(tipo);
		add(btnIndietro);
		
		btnRegistrazione = new JButton("REGISTRATI");
		btnRegistrazione.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRegistrazione.setBounds(236, 319, 130, 41);
		bottoneRegistrazione(tipo);
		add(btnRegistrazione);
		
		campoNome = new JTextField();
		campoNome.setColumns(10);
		campoNome.setBounds(247, 57, 263, 28);
		add(campoNome);
		
		campoCognome = new JTextField();
		campoCognome.setColumns(10);
		campoCognome.setBounds(247, 127, 263, 28);
		add(campoCognome);
		
		campoUsername = new JTextField();
		campoUsername.setColumns(10);
		campoUsername.setBounds(247, 193, 263, 28);
		add(campoUsername);
		
		campoPassword = new JPasswordField();
		campoPassword.setBounds(247, 259, 263, 28);
		add(campoPassword);

	}

}
