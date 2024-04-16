package classi;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Vector;

public class Questionario implements Serializable {
	private String titolo, categoria, descrizione;
	private int nDomande;
	private boolean aPunti, aScadenza;
	private Calendar scadenza;
	private Vector domande;
	
	public Questionario(String titolo, String categoria, String descrizione, int nDomande, boolean aPunti,
			boolean aScadenza, Calendar scadenza) {
		this.titolo = titolo;
		this.categoria = categoria;
		this.descrizione = descrizione;
		this.nDomande = nDomande;
		this.aPunti = aPunti;
		this.aScadenza = aScadenza;
		this.scadenza = scadenza;
		domande = new Vector();
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public int getnDomande() {
		return nDomande;
	}

	public void setnDomande(int nDomande) {
		this.nDomande = nDomande;
	}

	public boolean isaPunti() {
		return aPunti;
	}

	public void setaPunti(boolean aPunti) {
		this.aPunti = aPunti;
	}

	public boolean isaScadenza() {
		return aScadenza;
	}

	public void setaScadenza(boolean aScadenza) {
		this.aScadenza = aScadenza;
	}

	public Vector getDomande() {
		return domande;
	}

	public void setDomande(Vector domande) {
		this.domande = domande;
	}

	@Override
	public String toString() {
		if(aScadenza)
			return "Questionario [titolo=" + titolo + ", categoria=" + categoria + ", descrizione=" + descrizione
					+ ", nDomande=" + nDomande + ", aPunti=" + aPunti + ", aScadenza=" + aScadenza + ", scadenza="
					+ scadenza.get(Calendar.YEAR) + "/" + (scadenza.get(Calendar.MONTH)+1) + "/" + scadenza.get(Calendar.DAY_OF_MONTH)
					+ " hh:" + scadenza.get(Calendar.HOUR) + " mm:" + scadenza.get(Calendar.MINUTE) + ", domande=" + domande + "]";
		else
			return "Questionario [titolo=" + titolo + ", categoria=" + categoria + ", descrizione=" + descrizione
					+ ", nDomande=" + nDomande + ", aPunti=" + aPunti + ", aScadenza=" + aScadenza + "domande=" + domande + "]";
	}

}
