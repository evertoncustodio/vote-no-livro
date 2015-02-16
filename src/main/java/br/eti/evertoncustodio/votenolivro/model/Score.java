package br.eti.evertoncustodio.votenolivro.model;

import java.io.Serializable;

public class Score implements Serializable, Comparable<Score>{
	
	private static final long serialVersionUID = 1L;
	
	private Livro livro;
	private int votos;

	public Score(Livro livro, int votos) {
		validar(livro, votos);
		this.livro = livro;
		this.votos = votos;
	}

	private void validar(Livro livro, int votos) {
		if(livro == null) {
			throw new NullPointerException("Livro não preenchido");
		}
		
		if(votos < 0) { 
			throw new IllegalArgumentException("Número de votos inválido");
		}
	}
	
	public Livro getLivro() {
		return livro;
	}

	public int getVotos() {
		return votos;
	}

	public int compareTo(Score o) {
		return o.votos - this.votos;
	}
}
