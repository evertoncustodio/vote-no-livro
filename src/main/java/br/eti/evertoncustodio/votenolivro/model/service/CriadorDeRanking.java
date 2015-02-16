package br.eti.evertoncustodio.votenolivro.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.eti.evertoncustodio.votenolivro.model.Livro;
import br.eti.evertoncustodio.votenolivro.model.Ranking;
import br.eti.evertoncustodio.votenolivro.model.Score;
import br.eti.evertoncustodio.votenolivro.model.Usuario;
import br.eti.evertoncustodio.votenolivro.model.Votacao;
import br.eti.evertoncustodio.votenolivro.model.Voto;
import br.eti.evertoncustodio.votenolivro.model.dao.VotacaoDAO;

@Service
class CriadorDeRanking implements ICriadorDeRanking {

	@Resource
	private VotacaoDAO votacoes;
	
	public void setVotacoes(VotacaoDAO votacoes) {
		this.votacoes = votacoes;
	}

	public Ranking por(Usuario usuario) {
		return criar(votacoes.votacoesDo(usuario));
	}
	
	public Ranking geral() {
		return criar(votacoes.todas());
	}

	private Ranking criar(List<Votacao> todas) {
		if(todas.isEmpty()) {
			return Ranking.VAZIO;
		}
		
		Map<Livro, Integer> votos = new HashMap<Livro, Integer>();
		for(Votacao votacao: todas) {
			
			for(Voto v: votacao.getVotos()) {
				Integer votosNoLivro = votos.get(v.getOpcaoSelecionada());
				
				if(votosNoLivro == null) {
					votosNoLivro = 0;
				}
				
				votosNoLivro += 1;
				votos.put(v.getOpcaoSelecionada(), votosNoLivro);
			}
		}
		
		return new Ranking(criarScores(votos));
	}

	private List<Score> criarScores(Map<Livro, Integer> votos) {
		List<Score> scores = new ArrayList<Score>();
		for(Livro l: votos.keySet()) {
			Score s = new Score(l, votos.get(l));
			scores.add(s);
		}
		return scores;
	}
}
