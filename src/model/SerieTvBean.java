package model;

public class SerieTvBean extends ProdottoBean {

	private static final long serialVersionUID = 2656099471987189997L;
	private int numEpisodi;
	
	public SerieTvBean() {
		super();
		this.numEpisodi = 0;
	}

	public int getNumEpisodi() {
		return numEpisodi;
	}

	public void setNumEpisodi(int numEpisodi) {
		this.numEpisodi = numEpisodi;
	}

	@Override
	public String toString() {
		return super.toString()+"SerieTvBean [numEpisodi=" + numEpisodi + "]";
	}
	
	@Override
	public boolean equals(Object other) {
		return super.equals(other);
	}
}
