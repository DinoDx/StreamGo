package model;

import java.io.Serializable;

public class RecensioneBean implements Serializable {

	private static final long serialVersionUID = 1L;

	String descrizione;
	int voto;
	int numProgressivo;
	String nomeUtente;
	String nomeProdotto;
	
	public RecensioneBean () {
		descrizione= "";
		voto= -1;
		numProgressivo = -1;
		nomeUtente = "";
		nomeProdotto = "";
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public int getVoto() {
		return voto;
	}

	public void setVoto(int voto) {
		this.voto = voto;
	}

	public int getNumProgressivo() {
		return numProgressivo;
	}

	public void setNumProgressivo(int numProgressivo) {
		this.numProgressivo = numProgressivo;
	}

	public String getNomeUtente() {
		return nomeUtente;
	}

	public void setNomeUtente(String nomeUtente) {
		this.nomeUtente = nomeUtente;
	}

	public String getNomeProdotto() {
		return nomeProdotto;
	}

	public void setNomeProdotto(String nomeProdotto) {
		this.nomeProdotto = nomeProdotto;
	}

	@Override
	public String toString() {
		return "RecensioneBean [descrizione=" + descrizione + ", voto=" + voto + ", numProgressivo=" + numProgressivo
				+ ", nomeUtente=" + nomeUtente + ", nomeProdotto=" + nomeProdotto + "]";
	}
	
	@Override 
	public boolean equals(Object other) {
		return (this.getNumProgressivo() == ((RecensioneBean)other).getNumProgressivo() && this.getNomeProdotto() == ((RecensioneBean)other).getNomeProdotto() && this.getNomeUtente() == ((RecensioneBean)other).getNomeUtente());
	}
	
}
