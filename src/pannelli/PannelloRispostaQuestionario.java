package pannelli;

import javax.swing.JPanel;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import classi.DomandaQuiz;
import classi.DomandaSondaggio;
import classi.GestioneDatabase;
import classi.Questionario;
import classi.QuestionarioSvolto;
import classi.RispostaQuiz;
import classi.RispostaSondaggio;

import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.JRadioButton;

public class PannelloRispostaQuestionario extends JPanel {
	
	private GestioneDatabase db;
	private Questionario questionario;
	
	private JButton btnIndietro;
	private JLabel lblNumDomanda;
	private JTextArea txtDomanda;
	private JButton btnPrecedente;
	private JButton btnSuccessivo;
	private JLabel lblRiga;
	private JLabel lblObbligatoria;
	
	private Vector bottoni;
	private Vector<Vector<Integer>> risposte;
	Vector<Vector<Integer>> risposteGiuste;
	Vector<Double> punteggi;
	private int pos;
	
	private JButton btnFinisciQuestionario;
	
	public void addAtomicComponents() {
		
		btnIndietro = new JButton("Indietro");
		btnIndietro.setBounds(10, 11, 89, 23);
		indietro();
		add(btnIndietro);
		
		lblNumDomanda = new JLabel(")");
		lblNumDomanda.setBounds(70, 78, 29, 14);
		add(lblNumDomanda);
		
		txtDomanda = new JTextArea();
		txtDomanda.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtDomanda.setEditable(false);
		txtDomanda.setBounds(115, 75, 471, 23);
		add(txtDomanda);
		
		btnPrecedente = new JButton("<--");
		btnPrecedente.setBounds(10, 427, 52, 23);
		add(btnPrecedente);
		
		btnSuccessivo = new JButton("-->");
		btnSuccessivo.setBounds(639, 427, 52, 23);
		add(btnSuccessivo);
		
		lblRiga = new JLabel("______________________________________________________________________________________________");
		lblRiga.setBounds(26, 402, 675, 14);
		add(lblRiga);
		
		successivo();
		precedente();
		abilitaDisabilitaBottoniSwipe();
		addContentAndButtons();
	}
	
	public void addContentAndButtons() {
		bottoni = new Vector();
		DomandaQuiz domanda = (DomandaQuiz) questionario.getDomande().get(pos);
		int size = domanda.getRisposteQuiz().size();
		
		lblNumDomanda.setText(Integer.toString(domanda.getId()+1)+")");
		txtDomanda.setText(domanda.getQuesito());
		
		if(domanda.isObbligatoria()) {
			lblObbligatoria = new JLabel("! Obbligatoria");
			lblObbligatoria.setForeground(Color.RED);
			lblObbligatoria.setBounds(595, 77, 100, 14);
			add(lblObbligatoria);
		}
		
		if(domanda.isPiuRisposteGiuste()) {
			for(int i=0; i<size; i++) {
				String risposta = ((RispostaSondaggio) domanda.getRisposteQuiz().get(i)).getContenuto();
				JCheckBox chk = new JCheckBox(risposta);
				chk.setBounds(70, 143+(i*30), 471, 23);
				
				
				if(risposte.get(pos)!=null) //per selezionare le risposte che ha selezionato l'utente anche quando swipa
				{
					for(int j=0; j<risposte.get(pos).size(); j++) {
						if(risposte.get(pos).get(j)==i)
							chk.setSelected(true);
					}
				}
				
				add(chk);
				bottoni.add(chk);
			}
		}
		
		else {
			ButtonGroup gruppo = new ButtonGroup();
			
			for(int i=0; i<size; i++) {
				String risposta = ((RispostaSondaggio) domanda.getRisposteQuiz().get(i)).getContenuto();
				JRadioButton rdb = new JRadioButton(risposta);
				rdb.setBounds(70, 143+(i*30), 471, 23);
				add(rdb);
				
				if(risposte.get(pos)!=null)
				{
					for(int j=0; j<risposte.get(pos).size(); j++) {
						if(risposte.get(pos).get(j)==i)
							rdb.setSelected(true);
					}
				}
				
				bottoni.add(rdb);
				gruppo.add(rdb);
			}
		}
		
		if(pos==risposte.size()-1) {
			btnFinisciQuestionario = new JButton("Consegna il questionario");
			btnFinisciQuestionario.setBounds(260, 350, 200, 25);
			finisciQuestionario();
			add(btnFinisciQuestionario);
		}
	}
	
	public void salvaRispostaNelVettore() {
		Vector<Integer> risp = new Vector<Integer>();
		boolean check = false;
		
		for(int i=0; i<bottoni.size(); i++) {
			if(((AbstractButton) bottoni.get(i)).isSelected()) {
				risp.addElement(i);
				check=true;
			}
		}
		
		if(!check) risp=null;
		risposte.setElementAt(risp, pos);
		
		System.out.println(risposte+"\n");
	}
	
	public void successivo() {
		btnSuccessivo.addActionListener(new ActionListener() {
	           @Override
	           public void actionPerformed(ActionEvent e) {
	        	   salvaRispostaNelVettore();
	        	   svuota();
	        	   pos++;
	        	   addAtomicComponents();
        	   }
	       });
	}
	
	public void precedente() {
		btnPrecedente.addActionListener(new ActionListener() {
	           @Override
	           public void actionPerformed(ActionEvent e) {
	        	   salvaRispostaNelVettore();
	        	   svuota();
	        	   pos--;
	        	   addAtomicComponents();
	           }
	       });
	}
	
	public void indietro() { //dialogue box che si attiva se si preme il bottone "Indietro"
		btnIndietro.addActionListener(new ActionListener() {
	           @Override
	           public void actionPerformed(ActionEvent e) {
	        	   int n = JOptionPane.showConfirmDialog(
	        			   PannelloRispostaQuestionario.this,
	        			    "Vuoi tornare indietro e non rispondere al questionario?\nUna volta confermato, le risposte date verranno cancellate e non saranno inviate.",
	        			    "Annulla risposta",
	        			    JOptionPane.YES_NO_OPTION);
	        	   if(n==0) { //se la risposta è sì
	        		   ((Frame) SwingUtilities.getWindowAncestor(PannelloRispostaQuestionario.this)).switchToHomeUser();
	        	   }
	           }
	       });
	}
	
	public void finisciQuestionario() {
		btnFinisciQuestionario.addActionListener(new ActionListener() {
	           @Override
	           public void actionPerformed(ActionEvent e) {
	        	   salvaRispostaNelVettore();
	        	   
	        	   if(!controlli()) {
	        		   JOptionPane.showMessageDialog(PannelloRispostaQuestionario.this,
		    				    "Non puoi lasciare le risposte obbligatorie vuote!",
		    				    "Errore nella risposta del questionario",
		    				    JOptionPane.ERROR_MESSAGE);
	        	   }
	        	   else {
		        	   Calendar dataDiConsegna = Calendar.getInstance();
		        	   QuestionarioSvolto rispostaQuestionario = new QuestionarioSvolto(risposte, null, dataDiConsegna);
		        	   new GestioneDatabase().salvaRispostaSuFile(rispostaQuestionario);
		        	   JOptionPane.showMessageDialog(PannelloRispostaQuestionario.this,
		    				    "Questionario inviato con successo!",
		    				    "Questionario completato",
		    				    JOptionPane.PLAIN_MESSAGE);
		        	   
		        	   if(questionario.isaPunti()) {
		        		   JOptionPane.showMessageDialog(PannelloRispostaQuestionario.this,
			    				    "Punteggio: "+punteggio(),
			    				    "Questionario completato",
			    				    JOptionPane.PLAIN_MESSAGE);
		        	   }
	        	   }
        	   }
	       });
	}
	
	public boolean controlli() { //controlla che le domande obbligatorie sono state risposte
		boolean check=true;
		
		for(int i=0; i<risposte.size(); i++) {
			if(((DomandaSondaggio) questionario.getDomande().get(i)).isObbligatoria() && risposte.get(i) == null) {
				check=false;
				break;
			}
		}
		
		return check;
	}
	
	public void ottieniRisposteEPunteggi() {
		risposteGiuste = new Vector<Vector<Integer>>();
		punteggi = new Vector<Double>();
		Vector<Integer> risp;
		DomandaQuiz tmp;
		
		for(int i=0; i<risposte.size(); i++) { //ottieni i vettori delle risposte corrette e dei punteggi
			risp = new Vector<Integer>();
			
			tmp = ((DomandaQuiz) questionario.getDomande().get(i));
			punteggi.add(tmp.getPunteggio());
			
			System.out.println(tmp.getRisposteQuiz().size());
						
			for(int j=0; j<tmp.getRisposteQuiz().size(); j++) {
				if( ((RispostaQuiz) tmp.getRisposteQuiz().get(j)).isGiusta()) {
					risp.add(j);
				}
			}
			risposteGiuste.add(risp);
		}
	}
	
	public double punteggio() {
		double punteggio=0;

		for(int i=0; i<risposte.size(); i++) {
			if(risposte.get(i)!=null) {
				if(risposte.get(i).equals(risposteGiuste.get(i)) ){
					punteggio+=punteggi.get(i);
				}
			}
		}
		
		return punteggio;
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
	
	public PannelloRispostaQuestionario() {
		setLayout(null);
		
		pos = 0;
		
		db = new GestioneDatabase();
		questionario = db.getQuestionario();
		System.out.println(questionario);
		
		risposte = new Vector<Vector<Integer>>();
		
		for(int i=0; i<questionario.getDomande().size(); i++) {
			risposte.addElement(null);
		}
		
		System.out.println(risposte.size());
		
		if(questionario.isaPunti())
			ottieniRisposteEPunteggi();
		addAtomicComponents();
	}
}
