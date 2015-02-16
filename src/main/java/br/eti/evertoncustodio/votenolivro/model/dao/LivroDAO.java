package br.eti.evertoncustodio.votenolivro.model.dao;

import java.util.List;

import br.eti.evertoncustodio.votenolivro.model.Livro;

public interface LivroDAO {

	List<Livro> todos();
	Livro getLivro(Long id);
}
