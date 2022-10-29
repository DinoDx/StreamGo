package model;

public class FilmBean extends ProdottoBean {

	private static final long serialVersionUID = -8324325439034750425L;
	private String genere;

	public FilmBean() {
		super();
		this.genere="";
	}

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}

	@Override
	public String toString() {
		return super.toString()+"FilmBean [genere=" + genere + "]";
	}

	@Override
	public boolean equals(Object other) {
		return super.equals(other);
	}
}
