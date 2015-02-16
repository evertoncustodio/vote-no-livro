package br.eti.evertoncustodio.votenolivro.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ranking {

	public static final Ranking VAZIO = new Ranking();
	
	private List<Score> posicoes = new ArrayList<Score>();
	
	public Ranking(List<Score> posicoes) {
		if(posicoes == null) {
			throw new NullPointerException("Posições não pode ser nulo");
		}
		
		this.posicoes.addAll(posicoes);
		ordenar();
	}

	private void ordenar() {
		Collections.sort(this.posicoes);
	}
	
	private Ranking() {
		
	}
	
	public int tamanho() {
		return posicoes.size();
	}
	
	public boolean vazio() {
		return posicoes.isEmpty();
	}
	
	public List<Score> getScores() {
		return Collections.unmodifiableList(posicoes);
	}
}
