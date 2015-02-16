package br.eti.evertoncustodio.votenolivro.model.service;

import br.eti.evertoncustodio.votenolivro.model.Ranking;
import br.eti.evertoncustodio.votenolivro.model.Usuario;

public interface ICriadorDeRanking {

	Ranking por(Usuario usuario);
	Ranking geral();
}
