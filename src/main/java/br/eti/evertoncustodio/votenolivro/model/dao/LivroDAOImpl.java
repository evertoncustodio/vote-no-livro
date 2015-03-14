package br.eti.evertoncustodio.votenolivro.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.eti.evertoncustodio.votenolivro.model.Livro;

@Repository
class LivroDAOImpl implements LivroDAO {

	@PersistenceContext(unitName="default")
	private EntityManager manager;
	
	public void setManager(EntityManager manager) {
		this.manager = manager;
	}
	
	@Override
	public List<Livro> todos() {
		return manager.createQuery("select l from Livro l", Livro.class).getResultList();
	}

	@Override
	public Livro getLivro(Long id) {
		return manager.find(Livro.class, id);
	}
}
