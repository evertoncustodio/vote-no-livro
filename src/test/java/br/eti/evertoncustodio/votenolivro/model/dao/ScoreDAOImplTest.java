package br.eti.evertoncustodio.votenolivro.model.dao;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.eti.evertoncustodio.votenolivro.model.Livro;
import br.eti.evertoncustodio.votenolivro.model.Score;

public class ScoreDAOImplTest {

	private static JPAUtil jpaUtil;
	private static EntityManager manager;
	private ScoreDAOImpl scoreDAO;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		jpaUtil = new JPAUtil();
		manager = jpaUtil.getEntityManager();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		jpaUtil.close();
	}

	@Before
	public void setUp() throws Exception {
		scoreDAO = new ScoreDAOImpl();
		scoreDAO.setManager(manager);
		
		manager.getTransaction().begin();
	}

	@After
	public void tearDown() throws Exception {
		manager.getTransaction().rollback();
	}

	@Test
	public void deveCriarPrimeiroVoto() {
		Livro l1 = new Livro();
		l1.setTitulo("Harry Potter");
		
		manager.persist(l1);
		
		scoreDAO.votar(l1);
		
		assertEquals(1, getPorLivro(l1).getVotos());
	}
	
	@Test
	public void deveSomarVotos() {
		Livro l1 = new Livro();
		l1.setTitulo("Harry Potter");
		
		manager.persist(l1);
		
		scoreDAO.votar(l1);
		scoreDAO.votar(l1);
		
		assertEquals(2, getPorLivro(l1).getVotos());
	}
	
	@Test
	public void deveSomarVotosEmLivrosDiferentes() {
		Livro l1 = new Livro();
		l1.setTitulo("Harry Potter");
		
		Livro l2 = new Livro();
		l2.setTitulo("Effective Java");
		
		manager.persist(l1);
		manager.persist(l2);
		
		scoreDAO.votar(l1);
		scoreDAO.votar(l1);
		scoreDAO.votar(l2);
		
		assertEquals(2, getPorLivro(l1).getVotos());
		assertEquals(1, getPorLivro(l2).getVotos());
	}
	
	@Test
	public void deveListarVariosScores() {
		Livro l1 = new Livro();
		l1.setTitulo("Harry Potter");
		
		Livro l2 = new Livro();
		l2.setTitulo("Effective Java");
		
		Score s1 = new Score(l1, 10);
		Score s2 = new Score(l2, 5);
		
		manager.persist(l1);
		manager.persist(l2);
		manager.persist(s1);
		manager.persist(s2);
		
		assertEquals(2, scoreDAO.getLista().size());
	}
	
	@Test
	public void deveRetornarVazioQuandoNaoHouverScore() {
		assertTrue(scoreDAO.getLista().isEmpty());
	}
	
	private Score getPorLivro(Livro livro) {
		TypedQuery<Score> query = manager.createQuery("select s from Score s where s.livro = :livro", Score.class);
		query.setParameter("livro", livro);
		
		return query.getSingleResult();
	}

}
