package model;

import java.io.Serializable;
import java.util.ArrayList;


public class UtenteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	private String nome;
	private String cognome;
	private boolean admin;
	private ArrayList<String> telefoni;
	private ArrayList<RecensioneBean> recensioni;

	public UtenteBean() {
		username = "";
		password = "";
		nome = "";
		cognome = "";
		admin = false;
		telefoni = new ArrayList<String>();
		recensioni = new ArrayList<RecensioneBean>();
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

	public boolean isAdmin() {
		return admin;
	}

	
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public ArrayList<RecensioneBean> getRecensioni() {
		return recensioni;
	}

	public void setRecensioni(ArrayList<RecensioneBean> recensioni) {
		this.recensioni = recensioni;
	}
	
	public void addRecensioni(RecensioneBean recensione) {
		recensioni.add(recensione);
	}

	public void setTelefoni(ArrayList<String> telefoni) {
		this.telefoni = telefoni;
	}

	public ArrayList<String> getTelefoni() {
		return telefoni;
	}

	public void addTelefono(String numero) {
		telefoni.add(numero);
	}
	
	public void removeTelefono(String numero) {
		telefoni.removeIf(t->t.equals(numero));
	}
	
	public int countRecensioni() {
		return recensioni.size();
	}

	public double mediaRecensioni() {
		if(recensioni.size() == 0)
				return 0;
		else {
			double sum = 0.0;
			for(RecensioneBean r : recensioni)
				sum += r.getVoto();
			
			return sum / recensioni.size();
		}
	}
	@Override 
	public boolean equals(Object other) {
		return (this.username == ((UtenteBean)other).getUsername());
	}

	@Override
	public String toString() {
		return "UtenteBean [username=" + username + ", password=" + password + ", nome=" + nome + ", cognome=" + cognome
				+ ", admin=" + admin + ", telefoni=" + telefoni + "]";
	}

	
	
}
