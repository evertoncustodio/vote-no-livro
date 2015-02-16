package br.eti.evertoncustodio.votenolivro.model.dao;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.eti.evertoncustodio.votenolivro.model.Livro;

public class LivroDAOImplTest {

	private static JPAUtil jpaUtil;
	private static EntityManager manager;
	private LivroDAOImpl livroDAO;
	
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
		livroDAO = new LivroDAOImpl();
		livroDAO.setManager(manager);
		
		manager.getTransaction().begin();
	}

	@After
	public void tearDown() throws Exception {
		manager.getTransaction().rollback();
	}
	
	@Test
	public void deve_listar_livros_cadastrados() {
		incluir3Livros();
		assertEquals(3, livroDAO.todos().size());
	}
	
	@Test
	public void deve_retornar_vazio_quando_nao_houver() {
		assertTrue(livroDAO.todos().isEmpty());
	}

	private void incluir3Livros() {
		Livro l1 = new Livro();
		l1.setTitulo("Harry Potter");
		
		Livro l2 = new Livro();
		l2.setTitulo("Effective Java");
		
		Livro l3 = new Livro();
		l3.setTitulo("Head First: Servlets & JSP");
		
		manager.persist(l1);
		manager.persist(l2);
		manager.persist(l3);
	}
}
