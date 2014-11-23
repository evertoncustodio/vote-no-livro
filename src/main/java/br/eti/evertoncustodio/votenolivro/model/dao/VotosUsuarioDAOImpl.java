package br.eti.evertoncustodio.votenolivro.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.eti.evertoncustodio.votenolivro.model.VotosUsuario;

@Repository
class VotosUsuarioDAOImpl implements VotosUsuarioDAO {

	@PersistenceContext
	private EntityManager manager;
	
	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	@Override
	public void gravar(VotosUsuario votosUsuario) {
		manager.persist(votosUsuario);
	}
}
