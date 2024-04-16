package classi;

import java.io.Serializable;
import java.util.Vector;

public class DomandaQuiz extends DomandaSondaggio implements Serializable {
	
	private double punteggio;
	protected boolean piuRisposteGiuste;
	private Vector<RispostaQuiz> risposte;
	
	public DomandaQuiz(String quesito, boolean piuRisposteGiuste, boolean obbligatoria, int id, double punteggio) {
		super(quesito, obbligatoria, id);
		this.punteggio = punteggio;
		this.piuRisposteGiuste = piuRisposteGiuste;
	}

	public double getPunteggio() {
		return punteggio;
	}

	public void setPunteggio(double punteggio) {
		this.punteggio = punteggio;
	}

	public boolean isPiuRisposteGiuste() {
		return piuRisposteGiuste;
	}

	public void setPiuRisposteGiuste(boolean piuRisposteGiuste) {
		this.piuRisposteGiuste = piuRisposteGiuste;
	}
	
	public Vector<RispostaQuiz> getRisposteQuiz() {
		return risposte;
	}

	public void setRisposteQuiz(Vector<RispostaQuiz> risposte) {
		this.risposte = risposte;
	}

	@Override
	public String toString() {
		return "DomandaQuiz [punteggio=" + punteggio + ", piuRisposteGiuste=" + piuRisposteGiuste + ", risposte="
				+ risposte + ", quesito=" + quesito + ", obbligatoria=" + obbligatoria + ", id=" + id + "]";
	}
	
}
