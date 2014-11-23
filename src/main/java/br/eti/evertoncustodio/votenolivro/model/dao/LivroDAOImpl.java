package br.eti.evertoncustodio.votenolivro.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.eti.evertoncustodio.votenolivro.model.Livro;

@Repository
class LivroDAOImpl implements LivroDAO {

	@PersistenceContext
	private EntityManager manager;
	
	public void setManager(EntityManager manager) {
		this.manager = manager;
	}
	
	public void adicionar(Livro livro) {
		manager.persist(livro);
	}

	public List<Livro> getLista() {
		TypedQuery<Livro> query = manager.createQuery("select l from Livro l", Livro.class);
		return query.getResultList();
	}
	
	public Livro getLivro(Long id) {
		return manager.find(Livro.class, id);
	}

}
