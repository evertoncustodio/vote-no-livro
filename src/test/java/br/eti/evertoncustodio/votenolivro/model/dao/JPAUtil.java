package br.eti.evertoncustodio.votenolivro.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class JPAUtil {

	private EntityManager manager;
	
	public JPAUtil() {
		manager = Persistence.createEntityManagerFactory("voteNoLivroTest").createEntityManager(); 
	}
	
	public EntityManager getEntityManager() {
		return manager;
	}
	
	public void close() {
		manager.close();
	}
}
