package br.eti.evertoncustodio.votenolivro.model.service;

import java.util.List;

import br.eti.evertoncustodio.votenolivro.model.Score;

public interface ScoreService {

	void votar(Long idLivro);
	List<Score> getLista();
	
}
