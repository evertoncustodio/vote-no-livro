package br.eti.evertoncustodio.votenolivro.model.dao;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.eti.evertoncustodio.votenolivro.model.Usuario;

public class UsuarioDAOImplTest {

	private static JPAUtil jpaUtil;
	private static EntityManager manager;
	
	private UsuarioDAOImpl usuarioDAO;
	
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
		manager.getTransaction().begin();
		usuarioDAO = new UsuarioDAOImpl();
		usuarioDAO.setManager(manager);
	}

	@After
	public void tearDown() throws Exception {
		manager.getTransaction().rollback();
	}

	@Test
	public void deveIncluirUsuario() {
		Usuario usuario = new Usuario();
		usuario.setNome("Everton");
		usuario.setEmail("everton@provedor.com");
		
		usuarioDAO.gravar(usuario);
		assertEquals(usuario, manager.find(Usuario.class, usuario.getId()));
	}
	
	@Test
	public void deveAtualizarUsuarioComEmailJaInformado() {
		Usuario u1 = new Usuario();
		u1.setNome("Everton");
		u1.setEmail("everton@provedor.com");
		
		Usuario u2 = new Usuario();
		u2.setNome("Henrique");
		u2.setEmail("everton@provedor.com");
		
		usuarioDAO.gravar(u1);
		usuarioDAO.gravar(u2);
		
		assertEquals(u1.getId(), u2.getId());
		assertEquals("Henrique", manager.find(Usuario.class, u1.getId()).getNome());
	}
	
	@Test(expected=NullPointerException.class)
	public void naoDeveGravarQuandoUsuarioEhNulo() {
		usuarioDAO.gravar(null);
	}

}
