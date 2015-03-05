package br.eti.evertoncustodio.votenolivro.controller.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import br.eti.evertoncustodio.votenolivro.model.Livro;
import br.eti.evertoncustodio.votenolivro.model.dao.LivroDAO;

public class StringParaLivro implements Converter<String, Livro> {

	@Autowired
	LivroDAO livros;
	
	@Override
	public Livro convert(String id) {
		return livros.getLivro(Long.valueOf(id));
	}
}
