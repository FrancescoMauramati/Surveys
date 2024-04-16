package classi;

import java.io.Serializable;

public class Utente implements Serializable {
	private String nome=null, cognome=null, username=null, password=null;

	public Utente(String nome, String cognome, String username, char[] password) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.password = new String(password);
		
		SHA256 sha256 = new SHA256();
		this.password = sha256.encrypt(this.password);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "Utente [nome=" + nome + ", cognome=" + cognome + ", username=" + username + ", password=" + password
				+ "]";
	}

}
