package pannelli;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import classi.GestioneDatabase;
import classi.Utente;

import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PannelloLogin extends JPanel {
	private JTextField campoUsername;
	private JPasswordField campoPassword;
	
	private JButton btnIndietro;
	private JButton btnAccesso;
	private JButton btnRegistrazione;
	
	private JLabel lblUsername;
	private JLabel lblPassword;
	
	public void indietro() {
		btnIndietro.addActionListener(new ActionListener() {
	           @Override
	           public void actionPerformed(ActionEvent e) {
	               ((Frame) SwingUtilities.getWindowAncestor(PannelloLogin.this)).switchToSchermataPrincipale();
	               campoUsername.setText(null);
            	   campoPassword.setText(null);
	           }
	       });
	}
	
	public void bottoneRegistrazione(String tipo) {
		btnRegistrazione.addActionListener(new ActionListener() {
	           @Override
	           public void actionPerformed(ActionEvent e) {
	               if(tipo.equals("utente"))
	            	   ((Frame) SwingUtilities.getWindowAncestor(PannelloLogin.this)).switchToRegistrazioneUtente();
	               else
	            	   ((Frame) SwingUtilities.getWindowAncestor(PannelloLogin.this)).switchToRegistrazioneAdmin();
	               campoUsername.setText(null);
            	   campoPassword.setText(null);
	           }
	       });
	}
	
	public void bottoneLogin(String tipo) {
		btnAccesso.addActionListener(new ActionListener() {
	           @Override
	           public void actionPerformed(ActionEvent e) {
	               GestioneDatabase r = new GestioneDatabase();
	               
	               String username = campoUsername.getText();
	               char tmp[] = campoPassword.getPassword();
	               String password = new String(tmp);
	               
	               Utente u = r.login(tipo, username, password);
	               
	               if(u!=null) {
	            	   if(tipo.equals("utente"))
	            		   ((Frame) SwingUtilities.getWindowAncestor(PannelloLogin.this)).switchToHomeUser();
	            	   else
	            		   ((Frame) SwingUtilities.getWindowAncestor(PannelloLogin.this)).switchToHomeAdmin();
	            	   
	            	   campoUsername.setText(null);
	            	   campoPassword.setText(null);
	               }
	               else
	            	   JOptionPane.showMessageDialog(PannelloLogin.this,
		       				    "Le credenziali inserite non sono corrette!",
		       				    "Errore nell'autenticazione",
		       				    JOptionPane.ERROR_MESSAGE);
	           }
	       });
	}
	
	
	
	/**
	 * Create the panel.
	 */
	public PannelloLogin(String tipo) {
		setLayout(null);
		
		btnIndietro = new JButton("INDIETRO");
		btnIndietro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnIndietro.setBounds(10, 11, 109, 34);
		indietro();
		add(btnIndietro);
		
		lblUsername = new JLabel("NOME UTENTE");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUsername.setBounds(245, 89, 109, 23);
		add(lblUsername);
		
		lblPassword = new JLabel("PASSWORD");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(255, 162, 89, 23);
		add(lblPassword);
		
		campoUsername = new JTextField();
		campoUsername.setBounds(195, 123, 208, 28);
		add(campoUsername);
		campoUsername.setColumns(10);
		
		campoPassword = new JPasswordField();
		campoPassword.setBounds(195, 196, 208, 28);
		add(campoPassword);
		
		btnAccesso = new JButton("ACCEDI");
		btnAccesso.setBounds(250, 260, 99, 34);
		bottoneLogin(tipo);
		add(btnAccesso);
		
		btnRegistrazione = new JButton("NON HO UN ACCOUNT");
		btnRegistrazione.setBounds(10, 319, 157, 34);
		bottoneRegistrazione(tipo);
		add(btnRegistrazione);

	}
}
