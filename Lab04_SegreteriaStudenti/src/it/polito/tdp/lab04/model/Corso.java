package it.polito.tdp.lab04.model;

public class Corso {
	
	private String codins;
	private int numeroCrediti;
	private String nome;
	private int pd;
	
	public Corso(String codins, int numeroCrediti, String nome, int pd) {
		this.codins = codins;
		this.numeroCrediti = numeroCrediti;
		this.nome = nome;
		this.pd = pd;
	}

	public String getCodins() {
		return codins;
	}

	public int getNumeroCrediti() {
		return numeroCrediti;
	}

	public String getNome() {
		return nome;
	}

	public int getPd() {
		return pd;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codins == null) ? 0 : codins.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Corso other = (Corso) obj;
		if (codins == null) {
			if (other.codins != null)
				return false;
		} else if (!codins.equals(other.codins))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nome;
	}

}
