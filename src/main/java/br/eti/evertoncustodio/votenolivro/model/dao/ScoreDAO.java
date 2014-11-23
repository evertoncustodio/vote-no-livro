package br.eti.evertoncustodio.votenolivro.model.dao;

import java.util.List;

import br.eti.evertoncustodio.votenolivro.model.Livro;
import br.eti.evertoncustodio.votenolivro.model.Score;

public interface ScoreDAO {

	void votar(Livro livro);
	List<Score> getLista();
}
