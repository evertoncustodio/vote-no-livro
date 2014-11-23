package br.eti.evertoncustodio.votenolivro.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.eti.evertoncustodio.votenolivro.model.Usuario;

@Repository
public class UsuarioDAOImpl implements UsuarioDAO {

	@PersistenceContext
	private EntityManager manager;
	
	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	@Override
	public void gravar(Usuario usuario) {
		if(usuario == null) {
			throw new NullPointerException("usuario não pode ser nulo");
		}
		
		Usuario usuarioExistente = buscaPorEmail(usuario.getEmail());
		
		if(usuarioExistente != null) {
			usuario.setId(usuarioExistente.getId());
			manager.merge(usuario);
		} else {
			manager.persist(usuario);
		}
	}

	private Usuario buscaPorEmail(String email) {
		TypedQuery<Usuario> query = manager.createQuery("select u from Usuario u where u.email = :email", Usuario.class);
		query.setParameter("email", email);
		
		try {
			return query.getSingleResult();
		} catch(NoResultException e) {
			return null;
		}
	}
	
	
}
