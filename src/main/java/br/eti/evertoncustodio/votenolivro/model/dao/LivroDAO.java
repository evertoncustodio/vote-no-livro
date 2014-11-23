package br.eti.evertoncustodio.votenolivro.model.dao;

import java.util.List;

import br.eti.evertoncustodio.votenolivro.model.Livro;

public interface LivroDAO {

	void adicionar(Livro livro);
	List<Livro> getLista();
	Livro getLivro(Long id);
}
