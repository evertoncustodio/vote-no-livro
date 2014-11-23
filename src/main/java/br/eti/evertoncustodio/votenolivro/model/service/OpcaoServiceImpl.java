package br.eti.evertoncustodio.votenolivro.model.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.eti.evertoncustodio.votenolivro.model.Livro;
import br.eti.evertoncustodio.votenolivro.model.Opcao;
import br.eti.evertoncustodio.votenolivro.model.dao.LivroDAO;

@Service
class OpcaoServiceImpl implements OpcaoService {

	@Resource
	private LivroDAO livroDAO;
	
	public void setLivroDAO(LivroDAO livroDAO) {
		this.livroDAO = livroDAO;
	}

	@Override
	public List<Opcao> getOpcoes() {
		List<Livro> livros = livroDAO.getLista();
		
		List<Opcao> opcoes = new ArrayList<Opcao>();
		for(int i = 0; i < livros.size(); i++) {
			
			for(int j = (i + 1); j < livros.size(); j++) {
				Opcao opcao = new Opcao(livros.get(i), livros.get(j));
				opcoes.add(opcao);
			}
			
		}
		
		return opcoes;
	}

}
