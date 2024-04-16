package classi;
import java.io.Serializable;
import java.util.Vector;

public class DomandaSondaggio implements Serializable {
	protected String quesito;
	protected boolean obbligatoria;
	protected int id;
	private Vector<RispostaSondaggio> risposte;
	
	public DomandaSondaggio(String quesito, boolean obbligatoria, int id) {
		this.quesito = quesito;
		this.obbligatoria = obbligatoria;
		this.id = id;
	}

	public String getQuesito() {
		return quesito;
	}

	public void setQuesito(String quesito) {
		this.quesito = quesito;
	}
	
	public boolean isObbligatoria() {
		return obbligatoria;
	}

	public void setObbligatoria(boolean obbligatoria) {
		this.obbligatoria = obbligatoria;
	}

	public Vector<RispostaSondaggio> getRisposte() {
		return risposte;
	}

	public void setRisposte(Vector<RispostaSondaggio> risposte) {
		this.risposte = risposte;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Domanda [quesito=" + quesito + ", obbligatoria=" + obbligatoria +  ", id=" + id + ", risposte=" + risposte + "]";
	}	
}
