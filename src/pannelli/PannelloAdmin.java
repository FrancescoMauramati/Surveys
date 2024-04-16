package pannelli;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PannelloAdmin extends JPanel {
	
	private JButton btnDisconnetti;
	private JButton btnCreaSondaggio;
	/**
	 * Create the panel.
	 */
	public void disconnetti() {
		btnDisconnetti.addActionListener(new ActionListener() {
	           @Override
	           public void actionPerformed(ActionEvent e) {
	        	   int n = JOptionPane.showConfirmDialog(
	        			   PannelloAdmin.this,
	        			    "Vuoi disconnetterti dall'account e tornare alla schermata principale?",
	        			    "Disconnessione",
	        			    JOptionPane.YES_NO_OPTION);
	        	   if(n==0) { //se la risposta è sì
	        		   ((Frame) SwingUtilities.getWindowAncestor(PannelloAdmin.this)).switchToSchermataPrincipale();
	        	   }
	           }
	       });
	}
	
	public void creaSondaggio() {
		btnCreaSondaggio.addActionListener(new ActionListener() {
	           @Override
	           public void actionPerformed(ActionEvent e) {
	        	   ((Frame) SwingUtilities.getWindowAncestor(PannelloAdmin.this)).switchToCreazioneQuestionario();
	           }
	       });
	}
	
	
	public PannelloAdmin() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("SEI NELLA HOME ADMIN");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(229, 137, 149, 14);
		add(lblNewLabel);
		
		btnDisconnetti = new JButton("DISCONNETTI");
		btnDisconnetti.setBounds(10, 11, 115, 23);
		disconnetti();
		add(btnDisconnetti);
		
		btnCreaSondaggio = new JButton("CREA SONDAGGIO");
		btnCreaSondaggio.setBounds(216, 196, 167, 23);
		creaSondaggio();
		add(btnCreaSondaggio);

	}
}
