package classi;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Vector;

public class QuestionarioSvolto implements Serializable {
	private Vector<Vector<Integer>> risposte;
	private Utente nomeUtente;
	private Calendar dataDiConsegna;
	
	public QuestionarioSvolto(Vector<Vector<Integer>> risposte, Utente nomeUtente, Calendar dataDiConsegna) {
		super();
		this.risposte = risposte;
		this.nomeUtente = nomeUtente;
		this.dataDiConsegna = dataDiConsegna;
	}

	public Vector<Vector<Integer>> getRisposte() {
		return risposte;
	}

	public void setRisposte(Vector<Vector<Integer>> risposte) {
		this.risposte = risposte;
	}

	public Utente getNomeUtente() {
		return nomeUtente;
	}

	public void setNomeUtente(Utente nomeUtente) {
		this.nomeUtente = nomeUtente;
	}

	public Calendar getDataDiConsegna() {
		return dataDiConsegna;
	}

	public void setDataDiConsegna(Calendar dataDiConsegna) {
		this.dataDiConsegna = dataDiConsegna;
	}

	@Override
	public String toString() {
		return "QuestionarioSvolto [risposte=" + risposte + ", nomeUtente=" + nomeUtente + ", dataDiConsegna="
				+ dataDiConsegna + "]";
	}
	
	
}
