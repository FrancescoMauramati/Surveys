package pannelli;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

import classi.DomandaSondaggio;
import classi.GestioneDatabase;
import classi.DomandaQuiz;
import classi.Questionario;
import classi.RispostaSondaggio;
import classi.RispostaQuiz;

import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import com.toedter.components.JSpinField;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.util.Vector;
import javax.swing.JSpinner;

public class PannelloCreazioneDomandeSondaggio extends JPanel {
	private JTextField txtDomanda;
	private JLabel lblNumDomanda;
	private JButton btnIndietro;
	private JLabel lblRiga;
	private JButton btnAggiungiRisposta;
	private JButton btnPrecedente;
	private JButton btnSuccessivo;
	private JRadioButton rdbtnObbligatoria;
	private JButton btnFinisciSondaggio;
	
	private Questionario questionario;
	
	private Vector<ComponenteRispostaSondaggio> componentiRisposte = new Vector<ComponenteRispostaSondaggio>();
	private Vector<RispostaSondaggio> risposte = new Vector<RispostaSondaggio>();
	private Vector<DomandaSondaggio> domande;
	private int pos;
	
	public void inizializzaPannello(boolean appenaCreato) {
		componentiRisposte = new Vector<ComponenteRispostaSondaggio>();
		
		txtDomanda = new JTextField();
		txtDomanda.setBounds(66, 67, 624, 20);
		add(txtDomanda);
		txtDomanda.setColumns(10);
		
		lblNumDomanda = new JLabel((pos+1)+")");
		lblNumDomanda.setBounds(25, 72, 40, 14);
		add(lblNumDomanda);
		
		btnIndietro = new JButton("Indietro");
		btnIndietro.setBounds(10, 11, 89, 23);
		add(btnIndietro);
		
		btnAggiungiRisposta = new JButton("Aggiungi nuova risposta");
		add(btnAggiungiRisposta);
		
		lblRiga = new JLabel("_____________________________________________________________________________________________");
		lblRiga.setBounds(25, 382, 675, 14);
		add(lblRiga);
		
		btnPrecedente = new JButton("<--");
		btnPrecedente.setBounds(10, 407, 52, 23);
		add(btnPrecedente);
		
		btnSuccessivo = new JButton("-->");
		btnSuccessivo.setBounds(638, 407, 52, 23);
		add(btnSuccessivo);
		
		rdbtnObbligatoria = new JRadioButton("Obbligatoria");
		rdbtnObbligatoria.setBounds(86, 407, 109, 23);
		add(rdbtnObbligatoria);
		
		if(questionario.getnDomande()==(pos+1)) {
			btnFinisciSondaggio = new JButton("Finisci sondaggio");
			btnFinisciSondaggio.setBounds(275, 348, 149, 23);
			finisciSondaggio();
			add(btnFinisciSondaggio);
		}
		
		if(appenaCreato) {
			creaComponenteRisposta(null);
			domande.addElement(null);
		}
		
		aggiungiRisposta();
		successivo();
		precedente();
		abilitaDisabilitaBottoniSwipe();
		indietro();
	}
	
	public void aggiungiRisposta() { //listener del pulsante Aggiungi nuova Risposta, che aggiunge un campo risposta
		btnAggiungiRisposta.addActionListener(new ActionListener() {
	           @Override
	           public void actionPerformed(ActionEvent e) {
	        	   if(componentiRisposte.size()<10)
	        		   creaComponenteRisposta(null);
	        	   else
	        		   JOptionPane.showMessageDialog(PannelloCreazioneDomandeSondaggio.this,
	       				    "Il numero massimo di risposte è 10!",
	       				    "Errore nella creazione delle domande",
	       				    JOptionPane.ERROR_MESSAGE);
	        		   
	           }
	       });
	}
	
	public void creaComponenteRisposta(String txt) { //crea un campo per inserire una risposta
		btnAggiungiRisposta.setBounds(10, 202+(30*componentiRisposte.size()), 180, 23);
		
		JTextField txtRisposta = new JTextField(); //il campo dove scrivere il contenuto della risposta
 	    txtRisposta.setBounds(66, 147+(30*componentiRisposte.size()), 509, 20);  
 	    txtRisposta.setColumns(10);
 	    add(txtRisposta);
	       		
	    JRadioButton rdbtnRadioButton = new JRadioButton(""); //il radio button è solo per estetica, non serve a nulla
	    rdbtnRadioButton.setEnabled(false);
	    rdbtnRadioButton.setBounds(25, 147+(30*componentiRisposte.size()), 21, 23);
	    add(rdbtnRadioButton);
			       		
		JButton btnEliminaRisposta = new JButton("Cancella"); //il tasto che cancella la risposta scritta
		btnEliminaRisposta.setBounds(585, 145+(30*componentiRisposte.size()), 83, 23);
		add(btnEliminaRisposta);
			       
		int id = componentiRisposte.size(); //un numero identificativo della risposta, utile nella gestione nel vettore
			       
		ComponenteRispostaSondaggio risp = new ComponenteRispostaSondaggio(txtRisposta, rdbtnRadioButton, btnEliminaRisposta, id);
			       		
		componentiRisposte.addElement(risp);
		
		if(componentiRisposte.size()>6) {
			setPreferredSize(new Dimension(700, 460+(30*(componentiRisposte.size()-6)))); 
			lblRiga.setBounds(25, 382+(30*(componentiRisposte.size()-6)), 675, 14);
			rdbtnObbligatoria.setBounds(86, 407+(30*(componentiRisposte.size()-6)), 109, 23);
			btnPrecedente.setBounds(10, 407+(30*(componentiRisposte.size()-6)), 52, 23);
			btnSuccessivo.setBounds(638, 407+(30*(componentiRisposte.size()-6)), 52, 23);
			
			if(questionario.getnDomande()==(pos+1)) {
				btnFinisciSondaggio.setBounds(275, 348+(30*(componentiRisposte.size()-6)), 149, 23);
			}
		}
			       
		revalidate();
		repaint();
		
		eliminaRisposta(risp);
	}
	
	//listener del bottone eliminaRisposta, che elimina il campo relativo alla risposta del bottone cliccato
	public void eliminaRisposta(ComponenteRispostaSondaggio risp) {
		risp.getBtnEliminaRisposta().addActionListener(new ActionListener() {
	           @Override
	           public void actionPerformed(ActionEvent e) {
	        	   if(componentiRisposte.size()>1) { //almeno una risposta deve rimanere, non ci possono essere domande senza risposta
		        	   remove(risp.txtRisposta);
		        	   remove(risp.rdbtnRadioButton);
		        	   remove(risp.btnEliminaRisposta);
		        	   
		        	   componentiRisposte.removeElementAt(risp.getId());
		        	   
		        	   shift(risp.getId());
		        	   
		        	   revalidate();
		        	   repaint();
	        	   }
	           }
	       });
	}
	
	public void shift(int id) { //shifta i componenti in seguito a una cancellazione di una risposta
		
		//riassegna ai componenti il loro relativo id (shiftato)
		for(int i=id; i<componentiRisposte.size(); i++)
			componentiRisposte.get(i).setId(i);
 	   
		Rectangle r;
 	   
		//shifta tutti i componenti della risposta di uno spazio di 30 pixel più sopra
		for(int i=id; i<componentiRisposte.size(); i++) {
			r = componentiRisposte.get(i).getTxtRisposta().getBounds();
			componentiRisposte.get(i).getTxtRisposta().setBounds(r.x, r.y-30, r.width, r.height);
 		   
			r = componentiRisposte.get(i).getRdbtnRadioButton().getBounds();
			componentiRisposte.get(i).getRdbtnRadioButton().setBounds(r.x, r.y-30, r.width, r.height);

			r = componentiRisposte.get(i).getBtnEliminaRisposta().getBounds();
			componentiRisposte.get(i).getBtnEliminaRisposta().setBounds(r.x, r.y-30, r.width, r.height);
		}
 	   
		r = btnAggiungiRisposta.getBounds(); //e shifta anche il bottone per aggiungere una risposta
		btnAggiungiRisposta.setBounds(r.x, r.y-30, r.width, r.height);
		
		if(componentiRisposte.size()>5) {
			
			setPreferredSize(new Dimension(700, 460-(30*(componentiRisposte.size()-6))));
			
			r = lblRiga.getBounds();
			lblRiga.setBounds(r.x, r.y-30, r.width, r.height);
			
			r = rdbtnObbligatoria.getBounds();
			rdbtnObbligatoria.setBounds(r.x, r.y-30, r.width, r.height);
			
			r = btnPrecedente.getBounds();
			btnPrecedente.setBounds(r.x, r.y-30, r.width, r.height);
			
			r = btnSuccessivo.getBounds();
			btnSuccessivo.setBounds(r.x, r.y-30, r.width, r.height);
			
			if(questionario.getnDomande()==(pos+1)) {
				r = btnFinisciSondaggio.getBounds();
				btnFinisciSondaggio.setBounds(r.x, r.y-30, r.width, r.height);
			}
		}
	}
	
	public void aggiungiDomanda() { //aggiunge/aggiorna il contenuto nel vettore domande
 	   risposte = new Vector<RispostaSondaggio>();
 	   String quesito=null;
 	   
 	   for(int i=0; i<componentiRisposte.size(); i++) {
 		   quesito = componentiRisposte.get(i).getTxtRisposta().getText();
 		   
  		   RispostaSondaggio r = new RispostaSondaggio(quesito, i);
 		   risposte.addElement(r);
 	   }
 	   
 	   quesito = txtDomanda.getText();
 	   boolean obbligatoria = rdbtnObbligatoria.isSelected();
 	   
 	   DomandaSondaggio d = new DomandaSondaggio(quesito, obbligatoria, pos);
 	   d.setRisposte(risposte);
 	   domande.setElementAt(d, pos);
 	   
 	   System.out.println(d);
 	   System.out.println("pos = "+pos+" size = "+domande.size());
	}
	
	public void successivo() {
		btnSuccessivo.addActionListener(new ActionListener() {
	           @Override
	           public void actionPerformed(ActionEvent e) {
	        	   aggiungiDomanda();
        		   svuota();
	        	   pos++;
	        	   
	        	   if(pos==domande.size()) {
	        		   inizializzaPannello(true);
	        	   }
	        	   else {
	        		   swipeRisposta();
	        	   }
	           }
	       });
	}
	
	public void precedente() {
		btnPrecedente.addActionListener(new ActionListener() {
	           @Override
	           public void actionPerformed(ActionEvent e) {
	        	   aggiungiDomanda();
        		   svuota();
        		   pos--;
        		   swipeRisposta();
	           }
	       });
	}
	
	public void indietro() { //dialogue box che si attiva se si preme il bottone "Indietro"
		btnIndietro.addActionListener(new ActionListener() {
	           @Override
	           public void actionPerformed(ActionEvent e) {
	        	   int n = JOptionPane.showConfirmDialog(
	        			   PannelloCreazioneDomandeSondaggio.this,
	        			    "Vuoi annullare la creazione del questionario?\nUna volta confermato, le informazioni inserite andranno perse.",
	        			    "Annulla creazione",
	        			    JOptionPane.YES_NO_OPTION);
	        	   if(n==0) { //se la risposta è sì
	        		   ((Frame) SwingUtilities.getWindowAncestor(PannelloCreazioneDomandeSondaggio.this)).switchToHomeAdmin();
	        	   }
	           }
	       });
	}
	
	public void finisciSondaggio() {
		btnFinisciSondaggio.addActionListener(new ActionListener() {
	           @Override
	           public void actionPerformed(ActionEvent e) {
	        	   int n = JOptionPane.showConfirmDialog(
	        			   PannelloCreazioneDomandeSondaggio.this,
	        			    "Confermi di voler creare il questionario? Una volta confermato, non sarà più possibile modificare il questionario.",
	        			    "Creazione questionario",
	        			    JOptionPane.YES_NO_OPTION);
	        	   if(n==0) { //se la risposta è sì
		        	   aggiungiDomanda();
		        	   
	        		   questionario.setDomande(domande);
	        		   
	        		   GestioneDatabase db = new GestioneDatabase();
	        		   db.salvaQuestionarioSuFile(questionario);
	        		   
	        		   JOptionPane.showMessageDialog(PannelloCreazioneDomandeSondaggio.this,
	       				    "Questionario creato con successo!",
	       				    "Creazione questionario",
	       				    JOptionPane.INFORMATION_MESSAGE );
	        		   
	        		   System.out.println(questionario);
	        		   
	        		   ((Frame) SwingUtilities.getWindowAncestor(PannelloCreazioneDomandeSondaggio.this)).switchToHomeAdmin();
	        	   }
	           }
	       });
	}
	
	public void swipeRisposta() { //scorre tra le risposte quando clicchi i tasti precedente e successivo
		inizializzaPannello(false);
		   
		txtDomanda.setText(domande.get(pos).getQuesito());
		rdbtnObbligatoria.setSelected(domande.get(pos).isObbligatoria());
		   
		Vector<RispostaSondaggio> tmp = domande.get(pos).getRisposte();
		   
		for(int i=0; i<tmp.size(); i++)
			creaComponenteRisposta(tmp.get(i).getContenuto());
		
	}
	
	public void svuota() { //cancella tutto il contenuto del pannello
		removeAll();
		revalidate();
		repaint();
	}
	
	public void abilitaDisabilitaBottoniSwipe() {
		if(questionario.getnDomande()==(pos+1)) {
 		    btnSuccessivo.setEnabled(false);
 	    }
		else
			btnSuccessivo.setEnabled(true);
		
 	    if(pos==0) {
 		    btnPrecedente.setEnabled(false);
 	    }
 	    else
 	    	btnPrecedente.setEnabled(true);
	}
	
	public PannelloCreazioneDomandeSondaggio(Questionario q) {
		//REMINDER: dopo 6 risposte, bisogna shiftare gli elementi sotto. METTERE UN LIMITE DI 10 DOMANDE MAX
		questionario = q;
		System.out.println(q);
		
		domande = new Vector<DomandaSondaggio>();
		
		pos=0;
		
		setLayout(null);
		
		inizializzaPannello(true);
	}
}
