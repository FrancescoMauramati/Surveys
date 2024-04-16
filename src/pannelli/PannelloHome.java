package pannelli;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PannelloHome extends JPanel {
	JLabel lblPrincipale;
	JButton btnUtente;
	JButton btnAdmin;
	
	public void bottoneUtente() { //dà al bottone "utente" l'azione di passare alla schermata di login utente
		btnUtente.addActionListener(new ActionListener() {
	           @Override
	           public void actionPerformed(ActionEvent e) {
	               // Passa alla schermata di login utente
	               ((Frame) SwingUtilities.getWindowAncestor(PannelloHome.this)).switchToLoginUtente();
	           }
	       });
	}
	
	public void bottoneAdmin() { //dà al bottone "amministratore" l'azione di passare alla schermata di login dell'amministratore
		btnAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Passa alla schermata di login amministratore
                ((Frame) SwingUtilities.getWindowAncestor(PannelloHome.this)).switchToLoginAdmin();
            }
        });
	}
	/**
	 * Create the panel.
	 */
	public PannelloHome() {
		setLayout(null); //si imposta l'absolute layout
		
		lblPrincipale = new JLabel("EFFETTUA L'ACCESSO");
		lblPrincipale.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblPrincipale.setBounds(171, 70, 259, 30);
		add(lblPrincipale);
		
		btnUtente = new JButton("UTENTE");
		btnUtente.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnUtente.setBounds(43, 188, 126, 52);
		bottoneUtente();
		add(btnUtente);
		
		btnAdmin = new JButton("AMMINISTRATORE");
		btnAdmin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAdmin.setBounds(423, 188, 168, 52);
		bottoneAdmin();
		add(btnAdmin);
	}
}
