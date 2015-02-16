package br.eti.evertoncustodio.votenolivro.model.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.eti.evertoncustodio.votenolivro.model.Livro;
import br.eti.evertoncustodio.votenolivro.model.Votacao;
import br.eti.evertoncustodio.votenolivro.model.Voto;
import br.eti.evertoncustodio.votenolivro.model.dao.LivroDAO;

@Service
class CriadorDeVotacoes implements ICriadorDeVotacoes {

	@Resource
	private LivroDAO livros;
	
	public void setLivros(LivroDAO livros) {
		this.livros = livros;
	}

	public Votacao nova() {
		List<Voto> votos = criarVotos();
		Votacao votacao = new Votacao(votos);
		return votacao;
	}

	private List<Voto> criarVotos() {
		List<Livro> livrosDisponiveis = livros.todos();
		
		List<Voto> votos = new ArrayList<Voto>();
		for(int i = 0; i < livrosDisponiveis.size(); i++) {
			
			for(int j = (i + 1); j < livrosDisponiveis.size(); j++) {
				List<Livro> opcoes = Arrays.asList(livrosDisponiveis.get(i), livrosDisponiveis.get(j));
				
				Voto voto = new Voto(opcoes);
				votos.add(voto);
			}
			
		}
		
		return votos;
	}
}
