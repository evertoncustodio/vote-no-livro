package br.eti.evertoncustodio.votenolivro.model;

import java.io.Serializable;

public class Opcao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Livro opcao1;
	private Livro opcao2;
	
	public Opcao(Livro opcao1, Livro opcao2) {
		this.opcao1 = opcao1;
		this.opcao2 = opcao2;
	}

	public Livro getOpcao1() {
		return opcao1;
	}

	public Livro getOpcao2() {
		return opcao2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((opcao1 == null) ? 0 : opcao1.hashCode());
		result = prime * result + ((opcao2 == null) ? 0 : opcao2.hashCode());
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
		Opcao other = (Opcao) obj;
		if (opcao1 == null) {
			if (other.opcao1 != null)
				return false;
		} else if (!opcao1.equals(other.opcao1))
			return false;
		if (opcao2 == null) {
			if (other.opcao2 != null)
				return false;
		} else if (!opcao2.equals(other.opcao2))
			return false;
		return true;
	}
	
	
}
