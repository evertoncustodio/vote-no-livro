package br.eti.evertoncustodio.votenolivro.model.dao;

import java.util.List;

import br.eti.evertoncustodio.votenolivro.model.Usuario;
import br.eti.evertoncustodio.votenolivro.model.Votacao;

public interface VotacaoDAO {

	void gravar(Votacao votacao);
	List<Votacao> votacoesDo(Usuario usuario);
	List<Votacao> todas();
}
