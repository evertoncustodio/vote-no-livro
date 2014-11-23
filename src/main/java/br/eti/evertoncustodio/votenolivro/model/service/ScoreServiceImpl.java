package br.eti.evertoncustodio.votenolivro.model.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.eti.evertoncustodio.votenolivro.model.Livro;
import br.eti.evertoncustodio.votenolivro.model.Score;
import br.eti.evertoncustodio.votenolivro.model.dao.LivroDAO;
import br.eti.evertoncustodio.votenolivro.model.dao.ScoreDAO;

@Service
class ScoreServiceImpl implements ScoreService {

	@Resource
	private ScoreDAO scoreDAO;
	@Resource
	private LivroDAO livroDAO;
	
	public void setScoreDAO(ScoreDAO scoreDAO) {
		this.scoreDAO = scoreDAO;
	}
	
	public void setLivroDAO(LivroDAO livroDAO) {
		this.livroDAO = livroDAO;
	}

	public void votar(Long idLivro) {
		if(idLivro == null) {
			throw new NullPointerException("idLivro não pode ser nulo");
		}
		
		Livro livro = livroDAO.getLivro(idLivro);
		
		if(livro == null) {
			throw new RuntimeException("Livro não cadastrado");
		}
		
		scoreDAO.votar(livro);
	}

	public List<Score> getLista() {
		return scoreDAO.getLista();
	}

}
