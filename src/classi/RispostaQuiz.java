package classi;

import java.io.Serializable;

public class RispostaQuiz extends RispostaSondaggio implements Serializable {
	
	private boolean giusta;
	
	public RispostaQuiz(String contenuto, int id, boolean giusta) {
		super(contenuto, id);
		this.giusta = giusta;
	}

	public boolean isGiusta() {
		return giusta;
	}

	public void setGiusta(boolean giusta) {
		this.giusta = giusta;
	}

	@Override
	public String toString() {
		return "RispostaQuiz [contenuto=" + getContenuto() + ", id=" + getId() + "giusta= " + giusta + "]";
	}
	
}
