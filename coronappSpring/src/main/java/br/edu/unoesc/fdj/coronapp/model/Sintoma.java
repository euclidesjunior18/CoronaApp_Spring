package br.edu.unoesc.fdj.coronapp.model;

/**
 * Classe Sintoma
 * 
 * @author rober
 *
 */
public enum Sintoma {
	FEBRE("Febre", 15.0), 
	TOSSE("Tosse", 15.0), 
	DIFICULDADE_RESPIRAR("Dificuldade de Respirar", 20.0),
	DOR_ESTOMAGO("Dor de estômago", 10.0), 
	NAUSEA("Náusea", 10.0), 
	VOMITO("Vômito", 10.0), 
	DIARREIA("Náusea", 10.0),
	CANSACO("Cansaço", 10.0);

	private String descricao;
	private Double percentual;

	Sintoma(String descricao, Double percentual) {
		this.descricao = descricao;
		this.percentual = percentual;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the percentual
	 */
	public Double getPercentual() {
		return percentual;
	}

	/**
	 * @param percentual the percentual to set
	 */
	public void setPercentual(Double percentual) {
		this.percentual = percentual;
	}
}
