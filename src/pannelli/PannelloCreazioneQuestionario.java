package pannelli;

import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import classi.Questionario;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class PannelloCreazioneQuestionario extends JPanel {
	private JTextField txtCategoria;
	private JTextField txtTitolo;

	private JRadioButton rdbtnQuiz;
	private JRadioButton rdbtnSondaggio;
	private JRadioButton rdbtnSi;
	
	private JButton btnIndietro;
	private JButton btnCreaSondaggio;
	
	private JLabel lblTitolo;
	private JLabel lblCategoria;
	private JLabel lblNumeroDomande;
	private JLabel lblScadenza;
	private JLabel lblDescrizione;
	
	private JSpinner spinnerNumeroDomande;
	
	private JTextArea txtDescrizione;
	private JSpinner spinnerOra;
	private JSpinner spinnerMinuto;
	private JDateChooser spinnerGiorno;
	
	public void annulla() { //dialogue box che si attiva se si preme il bottone "Indietro"
		btnIndietro.addActionListener(new ActionListener() {
	           @Override
	           public void actionPerformed(ActionEvent e) {
	        	   int n = JOptionPane.showConfirmDialog(
	        			   PannelloCreazioneQuestionario.this,
	        			    "Vuoi annullare la creazione del questionario?\nUna volta confermato, le informazioni inserite andranno perse.",
	        			    "Annulla creazione",
	        			    JOptionPane.YES_NO_OPTION);
	        	   if(n==0) { //se la risposta è sì
	        		   ((Frame) SwingUtilities.getWindowAncestor(PannelloCreazioneQuestionario.this)).switchToHomeAdmin();
	        	   }
	           }
	       });
	}
	
	public void dataDiScadenza() { //abilita o disabilita la data di scadenza in base al valore del radio button "Sì"
		rdbtnSi.addActionListener(new ActionListener() {
	           @Override
	           public void actionPerformed(ActionEvent e) {
	        	   boolean value=rdbtnSi.isSelected();
	        	   spinnerGiorno.setEnabled(value);
	        	   spinnerOra.setEnabled(value);
	        	   spinnerMinuto.setEnabled(value);
	           }
	       });
	}
	
	public boolean controlli() { //controlla che i campi del titolo e categoria non siano vuoti e che la data inserita non sia già passata
		if(!txtTitolo.getText().equals("")) {
			if(!txtCategoria.getText().equals("")) {
				if(rdbtnSi.isSelected()) {
					Calendar dataAttuale = Calendar.getInstance(); //ottieni la data e orario attuali
					
					Calendar dataDiScadenza = spinnerGiorno.getCalendar(); //ricava la data inserita dall'utente
	        		int ora = (int) spinnerOra.getValue();
	        		int minuti = (int) spinnerMinuto.getValue();
	        		dataDiScadenza.set(dataDiScadenza.get(Calendar.YEAR), dataDiScadenza.get(Calendar.MONTH), dataDiScadenza.get(Calendar.DAY_OF_MONTH), ora, minuti);
	        		
	        		if(dataAttuale.compareTo(dataDiScadenza)<0) //se la data attuale è minore della data inserita in input
	        			return true;
	        		else {
	        			JOptionPane.showMessageDialog(PannelloCreazioneQuestionario.this,
		    				    "La data di scadenza del sondaggio non puo' essere una data passata!",
		    				    "Errore nella creazione del questionario",
		    				    JOptionPane.ERROR_MESSAGE);
	        			return false;
	        		}
				}
				else
					return true;
				
			}
			else {
				 JOptionPane.showMessageDialog(PannelloCreazioneQuestionario.this,
	    				    "La categoria del sondaggio non può essere vuota!",
	    				    "Errore nella creazione del questionario",
	    				    JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		else {
			JOptionPane.showMessageDialog(PannelloCreazioneQuestionario.this,
				    "Il nome del sondaggio non può essere vuoto!",
				    "Errore nella creazione del questionario",
				    JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	
	public void creaSondaggio() { //abilita o disabilita la data di scadenza in base al valore del radio button "Sì"
		btnCreaSondaggio.addActionListener(new ActionListener() {
	           @Override
	           public void actionPerformed(ActionEvent e) {
	        	   if(controlli()) {
		        	   boolean scadenza=rdbtnSi.isSelected();
		        	   boolean punti=rdbtnQuiz.isSelected();
		        	   String titolo = txtTitolo.getText();
		        	   String categoria = txtCategoria.getText();
		        	   int nDomande = (int) spinnerNumeroDomande.getValue();
		        	   String descrizione = txtDescrizione.getText();
		        	   Calendar dataDiScadenza = null;
		        	   if(scadenza) {
		        		   dataDiScadenza = spinnerGiorno.getCalendar();
		        		   int ora = (int) spinnerOra.getValue();
		        		   int minuti = (int) spinnerMinuto.getValue();
		        		   dataDiScadenza.set(dataDiScadenza.get(Calendar.YEAR), dataDiScadenza.get(Calendar.MONTH), dataDiScadenza.get(Calendar.DAY_OF_MONTH), ora, minuti);
		        	   }
		        	   
		        	   Questionario q = new Questionario(titolo, categoria, descrizione, nDomande, punti, scadenza, dataDiScadenza);
		        	   
		        	   if(punti)
		        		   ((Frame) SwingUtilities.getWindowAncestor(PannelloCreazioneQuestionario.this)).switchToCreazioneDomandeQuiz(q);
		        	   else
		        		   ((Frame) SwingUtilities.getWindowAncestor(PannelloCreazioneQuestionario.this)).switchToCreazioneDomandeSondaggio(q);
	        	   }
	           }
	       });
	}
	
	public PannelloCreazioneQuestionario() {
		setLayout(null);
		
		btnIndietro = new JButton("Indietro");
		btnIndietro.setBounds(10, 11, 89, 23);
		annulla();
		add(btnIndietro);
		
		rdbtnSondaggio = new JRadioButton("Sondaggio");
		rdbtnSondaggio.setBounds(78, 75, 89, 23);
		add(rdbtnSondaggio);
		
		rdbtnQuiz = new JRadioButton("Quiz");
		rdbtnQuiz.setBounds(10, 75, 66, 23);
		rdbtnQuiz.setSelected(true);
		add(rdbtnQuiz);
		
		ButtonGroup grp = new ButtonGroup();
		grp.add(rdbtnQuiz);
		grp.add(rdbtnSondaggio);
		
		lblTitolo = new JLabel("Titolo");
		lblTitolo.setBounds(227, 79, 46, 14);
		add(lblTitolo);
		
		lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(461, 79, 66, 14);
		add(lblCategoria);
		
		txtCategoria = new JTextField();
		txtCategoria.setBounds(530, 76, 117, 20);
		txtCategoria.setColumns(10);
		add(txtCategoria);
		
		txtTitolo = new JTextField();
		txtTitolo.setBounds(266, 76, 158, 20);
		add(txtTitolo);
		txtTitolo.setColumns(10);
		
		lblNumeroDomande = new JLabel("Numero di domande");
		lblNumeroDomande.setBounds(10, 121, 117, 14);
		add(lblNumeroDomande);
		
		lblScadenza = new JLabel("Data di scadenza");
		lblScadenza.setBounds(216, 121, 103, 14);
		add(lblScadenza);
		
		rdbtnSi = new JRadioButton("S\u00EC");
		rdbtnSi.setBounds(325, 117, 47, 23);
		dataDiScadenza();
		add(rdbtnSi);
		
		lblDescrizione = new JLabel("Descrizione");
		lblDescrizione.setBounds(11, 181, 88, 14);
		add(lblDescrizione);
		
		txtDescrizione = new JTextArea();
		txtDescrizione.setBounds(10, 206, 659, 143);
		add(txtDescrizione);
		
		spinnerNumeroDomande = new JSpinner();
		spinnerNumeroDomande.setBounds(137, 118, 40, 20);
		SpinnerNumberModel nDomande = new SpinnerNumberModel(1, 1, 100, 1);
		spinnerNumeroDomande.setModel(nDomande);
		add(spinnerNumeroDomande);
		
		spinnerOra = new JSpinner();
		spinnerOra.setBounds(518, 118, 42, 20);
		SpinnerNumberModel ore = new SpinnerNumberModel(0, 0, 23, 1); //da 0 a 23 ore
		spinnerOra.setModel(ore);
		spinnerOra.setEnabled(false);
		add(spinnerOra);
		
		spinnerMinuto = new JSpinner();
		spinnerMinuto.setBounds(604, 118, 46, 20);
		SpinnerNumberModel minuti = new SpinnerNumberModel(0, 0, 59, 1); //da 0 a 59 minuti
		spinnerMinuto.setModel(minuti);
		spinnerMinuto.setEnabled(false);
		add(spinnerMinuto);
		
		spinnerGiorno = new JDateChooser();
		spinnerGiorno.setBounds(378, 118, 103, 20);
		spinnerGiorno.setEnabled(false);
		add(spinnerGiorno);
		
		JLabel lblNewLabel = new JLabel("Ora");
		lblNewLabel.setBounds(491, 121, 30, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Minuti");
		lblNewLabel_1.setBounds(570, 121, 40, 14);
		add(lblNewLabel_1);
		
		btnCreaSondaggio = new JButton("Crea sondaggio");
		btnCreaSondaggio.setBounds(267, 376, 165, 23);
		creaSondaggio();
		add(btnCreaSondaggio);

	}
}
