package br.eti.evertoncustodio.votenolivro.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.eti.evertoncustodio.votenolivro.model.Livro;
import br.eti.evertoncustodio.votenolivro.model.Score;

@Repository
class ScoreDAOImpl implements ScoreDAO {

	@PersistenceContext
	private EntityManager manager;
	
	public void setManager(EntityManager manager) {
		this.manager = manager;
	}
	
	public void votar(Livro livro) {
		livro = manager.find(Livro.class, livro.getId());
		Score score = getPorLivro(livro);
	
		if(score == null) {
			score = new Score(livro, 1);
		} else {
			score.adicionarVoto();
		}
		
		manager.merge(score);
	}

	public List<Score> getLista() {
		TypedQuery<Score> query = manager.createQuery("select s from Score s", Score.class);
		return query.getResultList();
	}
	
	private Score getPorLivro(Livro livro) {
		try {
			TypedQuery<Score> query = manager.createQuery("select s from Score s where s.livro = :livro", Score.class);
			query.setParameter("livro", livro);
			
			return query.getSingleResult();
		} catch(NoResultException e) {
			return null;
		}
	}

}
