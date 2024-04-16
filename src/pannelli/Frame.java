package pannelli;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import classi.Questionario;

import java.awt.*;
import javax.swing.*;

public class Frame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -954828955778281306L;
	private JPanel contentPane;
	CardLayout cl = new CardLayout();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame frame = new Frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Frame() {
		
		//setResizable(true);
		setTitle("Surveys");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));

		setContentPane(contentPane);
		
		contentPane.setLayout(cl);
		
		JPanel schermataPrincipale = new PannelloHome();
		contentPane.add(schermataPrincipale, "1");
		
		JPanel schermataLoginUtente = new PannelloLogin("utente");
		contentPane.add(schermataLoginUtente, "2");
		
		JPanel schermataLoginAdmin = new PannelloLogin("admin");
		contentPane.add(schermataLoginAdmin, "3");
		
		JPanel schermataRegistrazioneUtente = new PannelloRegistrazione("utente");
		contentPane.add(schermataRegistrazioneUtente, "4");
		
		JPanel schermataRegistrazioneAdmin = new PannelloRegistrazione("admin");
		contentPane.add(schermataRegistrazioneAdmin, "5");
		
		JPanel schermataHomeUser = new PannelloUser();
		contentPane.add(schermataHomeUser, "6");
		
		JPanel schermataHomeAdmin = new PannelloAdmin();
		contentPane.add(schermataHomeAdmin, "7");
		
		cl.show(contentPane, "1");
		
	}
	
	public void switchToSchermataPrincipale() {
        cl.show(contentPane, "1");
        setTitle("Surveys");
    }
	public void switchToLoginUtente() {
        cl.show(contentPane, "2");
        setTitle("Surveys - Login utente");
    }
	public void switchToLoginAdmin() {
        cl.show(contentPane, "3");
        setTitle("Surveys - Login amministratore");
    }
	public void switchToRegistrazioneUtente() {
        cl.show(contentPane, "4");
        setTitle("Surveys - Registrazione utente");
    }
	public void switchToRegistrazioneAdmin() {
        cl.show(contentPane, "5");
        setTitle("Surveys - Registrazione amministratore");
    }
	public void switchToHomeUser() {
        cl.show(contentPane, "6");
        setTitle("Surveys");
    }
	public void switchToHomeAdmin() {
        cl.show(contentPane, "7");
        setTitle("Surveys");
    }
	public void switchToCreazioneQuestionario() {
		JPanel creazioneQuestionario = new PannelloCreazioneQuestionario();
		contentPane.add(creazioneQuestionario, "8");
        cl.show(contentPane, "8");
        setTitle("Surveys - Creazione Questionario");
    }
	public void switchToCreazioneDomandeQuiz(Questionario q) {
		JPanel creazioneDomande = new PannelloCreazioneDomandeQuiz(q);
		creazioneDomande.setPreferredSize(new Dimension(700, 460)); 
		JScrollPane scrollDomande = new JScrollPane(creazioneDomande);
		contentPane.add(scrollDomande, "9");
		
        cl.show(contentPane, "9");
        setTitle("Surveys - Creazione Questionario");
    }
	public void switchToCreazioneDomandeSondaggio(Questionario q) {
		JPanel creazioneDomande = new PannelloCreazioneDomandeSondaggio(q);
		creazioneDomande.setPreferredSize(new Dimension(700, 460)); 
		JScrollPane scrollDomande = new JScrollPane(creazioneDomande);
		contentPane.add(scrollDomande, "10");
		
        cl.show(contentPane, "10");
        setTitle("Surveys - Creazione Questionario");
    }
	public void switchToRispostaQuestionario() {
		JPanel rispostaQuestionario = new PannelloRispostaQuestionario();
		rispostaQuestionario.setPreferredSize(new Dimension(700, 460)); 
		JScrollPane scrollDomande = new JScrollPane(rispostaQuestionario);
		contentPane.add(scrollDomande, "11");
		
        cl.show(contentPane, "11");
        setTitle("Surveys - Risposta al Questionario");
    }

}
