package br.eti.evertoncustodio.votenolivro.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.eti.evertoncustodio.votenolivro.model.Usuario;
import br.eti.evertoncustodio.votenolivro.model.Votacao;

@Repository
class VotacaoDAOImpl implements VotacaoDAO {

	@PersistenceContext
	private EntityManager manager;
	
	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	@Override
	public void gravar(Votacao votacao) {
		manager.persist(votacao);		
	}

	@Override
	public List<Votacao> votacoesDo(Usuario usuario) {
		String strQuery = "select v from Votacao v where v.usuario.id = :usuarioId";
		TypedQuery<Votacao> query = manager.createQuery(strQuery, Votacao.class).setParameter("usuarioId", usuario.getId());
		return query.getResultList();
	}

	@Override
	public List<Votacao> todas() {
		return manager.createQuery("select v from Votacao v", Votacao.class).getResultList();
	}
}
