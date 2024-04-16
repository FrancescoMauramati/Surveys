package classi;

import java.io.Serializable;

public class RispostaSondaggio implements Serializable {
	private String contenuto;
	private int id;
	
	public RispostaSondaggio(String contenuto, int id) {
		this.contenuto = contenuto;
		this.id = id;
	}

	public String getContenuto() {
		return contenuto;
	}

	public void setContenuto(String contenuto) {
		this.contenuto = contenuto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Risposta [contenuto=" + contenuto + ", id=" + id + "]";
	}
	
}
