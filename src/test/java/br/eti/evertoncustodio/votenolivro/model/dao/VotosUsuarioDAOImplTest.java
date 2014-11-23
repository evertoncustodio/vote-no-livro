package br.eti.evertoncustodio.votenolivro.model.dao;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.eti.evertoncustodio.votenolivro.model.Livro;
import br.eti.evertoncustodio.votenolivro.model.Usuario;
import br.eti.evertoncustodio.votenolivro.model.VotosUsuario;

public class VotosUsuarioDAOImplTest {

	private static JPAUtil jpaUtil;
	private static EntityManager manager;
	
	private VotosUsuarioDAOImpl votosUsuarioDAO;
	
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
		votosUsuarioDAO = new VotosUsuarioDAOImpl();
		votosUsuarioDAO.setManager(manager);
		
		manager.getTransaction().begin();
	}

	@After
	public void tearDown() throws Exception {
		manager.getTransaction().rollback();
	}

	@Test
	public void deveGravarVotosUsuario() {
		Usuario u1 = new Usuario();
		u1.setNome("Everton");
		u1.setEmail("everton@provedor.com");
		
		manager.persist(u1);
		
		Livro l1 = new Livro();
		l1.setTitulo("Harry Potter");
		manager.persist(l1);
		
		VotosUsuario votosUsuario = new VotosUsuario();
		votosUsuario.adicionarVoto(l1);
		votosUsuario.setUsuario(u1);
		
		votosUsuarioDAO.gravar(votosUsuario);
		
		VotosUsuario votosGravados = manager.find(VotosUsuario.class, votosUsuario.getId());
		assertEquals("Everton", votosGravados.getUsuario().getNome());
		assertEquals(1, votosGravados.getRanking().getScores().size());
		assertEquals(l1, votosGravados.getRanking().getScores().get(0).getLivro());
	}

}
