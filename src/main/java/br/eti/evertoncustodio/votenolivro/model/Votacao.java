package br.eti.evertoncustodio.votenolivro.model;

import java.util.List;

public class Votacao {

	private List<Opcao> opcoes;
	private int posicao;
	
	public Votacao(List<Opcao> opcoes) {
		if(opcoes == null) {
			throw new NullPointerException("opcoes não pode ser nulo");
		}
		
		this.opcoes = opcoes;
	}
	
	public boolean temProximo() {
		return podeAvancar();
	}

	private boolean podeAvancar() {
		return (posicao + 1) < opcoes.size();
	}
	
	public int posicaoAtual() {
		return posicao;
	}
	
	public Opcao proximo() {
		if(podeAvancar()) {
			return opcoes.get(++posicao);
		}
		
		throw new IllegalStateException("Não existe próxima posição");
	}
	
	public Opcao atual() {
		return opcoes.get(posicao);
	}
}
