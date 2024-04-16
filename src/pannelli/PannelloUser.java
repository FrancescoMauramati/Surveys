package pannelli;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PannelloUser extends JPanel {
	
	JButton btnDisconnetti;
	JButton btnRispondiSondaggio;
	/**
	 * Create the panel.
	 */
	public void disconnetti() {
		btnDisconnetti.addActionListener(new ActionListener() {
	           @Override
	           public void actionPerformed(ActionEvent e) {
	        	   int n = JOptionPane.showConfirmDialog(
	        			    PannelloUser.this,
	        			    "Vuoi disconnetterti dall'account e tornare alla schermata principale?",
	        			    "Disconnessione",
	        			    JOptionPane.YES_NO_OPTION);
	        	   if(n==0) { //se la risposta è sì
	        		   ((Frame) SwingUtilities.getWindowAncestor(PannelloUser.this)).switchToSchermataPrincipale();
	        	   }
	           }
	       });
	}
	
	public void rispondiSondaggio() {
		btnRispondiSondaggio.addActionListener(new ActionListener() {
	           @Override
	           public void actionPerformed(ActionEvent e) {
	        	   ((Frame) SwingUtilities.getWindowAncestor(PannelloUser.this)).switchToRispostaQuestionario();
	           }
	       });
	}
	
	
	
	
	public PannelloUser() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("SEI NELLA HOME USER");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(229, 137, 142, 14);
		add(lblNewLabel);
		
		btnDisconnetti = new JButton("DISCONNETTI");
		btnDisconnetti.setBounds(10, 11, 115, 23);
		disconnetti();
		add(btnDisconnetti);
		
		btnRispondiSondaggio = new JButton("Rispondi al Questionario");
		btnRispondiSondaggio.setBounds(259, 162, 89, 23);
		rispondiSondaggio();
		add(btnRispondiSondaggio);

	}
}
