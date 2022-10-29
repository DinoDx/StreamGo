package model;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class ProdottoBean implements Serializable {

	private static final long serialVersionUID = 5372921610860216724L;
	private String nome;
	private int durata;
	private ArrayList<RecensioneBean> recensioni;
	private String link;
	private String img;
	
	public ProdottoBean() {
		this.nome="";
		this.durata=0;
		recensioni = new ArrayList<RecensioneBean>();
		this.link="";
		this.img="";
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getDurata() {
		return durata;
	}

	public void setDurata(int durata) {
		this.durata = durata;
	}
	
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
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
	public String toString() {
		return "ProdottoBean [nome=" + nome + ", durata=" + durata + "]";
	}
	
	@Override
	public boolean equals(Object other) {
		return this.getNome()==((ProdottoBean)other).getNome();
	}
}
