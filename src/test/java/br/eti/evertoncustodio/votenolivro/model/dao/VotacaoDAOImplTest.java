package br.eti.evertoncustodio.votenolivro.model.dao;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.eti.evertoncustodio.votenolivro.model.Livro;
import br.eti.evertoncustodio.votenolivro.model.Usuario;
import br.eti.evertoncustodio.votenolivro.model.Votacao;
import br.eti.evertoncustodio.votenolivro.model.Voto;

public class VotacaoDAOImplTest {

	private static JPAUtil jpaUtil;
	private static EntityManager manager;
	private static List<Livro> livros;
	
	private VotacaoDAOImpl votacaoDAO;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		jpaUtil = new JPAUtil();
		manager = jpaUtil.getEntityManager();
		incluir3Livros();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		jpaUtil.close();
	}

	@Before
	public void setUp() throws Exception {
		votacaoDAO = new VotacaoDAOImpl();
		votacaoDAO.setManager(manager);
		
		manager.getTransaction().begin();
	}

	@After
	public void tearDown() throws Exception {
		manager.getTransaction().rollback();
	}
	
	@Test
	public void deve_gravar_votacao() {
		Voto v1 = new Voto(Arrays.asList(livros.get(0), livros.get(1)));
		Votacao votacao = new Votacao(Arrays.asList(v1));
		
		votacaoDAO.gravar(votacao);
		
		assertEquals(votacao, manager.find(Votacao.class, votacao.getId()));
	}
	
	@Test
	public void deve_listar_votacoes_do_usuario() {
		Voto v1 = new Voto(Arrays.asList(livros.get(0), livros.get(1)));
		Votacao votacao1 = new Votacao(Arrays.asList(v1));
		Usuario usuario = new Usuario();
		usuario.setNome("Everton");
		usuario.setEmail("everton@provedor.com");
		votacao1.setUsuario(usuario);
		
		manager.persist(votacao1);
		
		Voto v2 = new Voto(Arrays.asList(livros.get(0), livros.get(1)));
		Votacao votacao2 = new Votacao(Arrays.asList(v2));
		Usuario usuario2 = new Usuario();
		usuario2.setNome("Jose");
		usuario2.setEmail("jose@provedor.com");
		votacao2.setUsuario(usuario2);
		
		manager.persist(votacao2);
		
		List<Votacao> votacoes = votacaoDAO.votacoesDo(usuario);
		assertEquals(1, votacoes.size());
		assertEquals(votacao1, votacoes.get(0));
	}
	
	@Test
	public void deve_listar_todas_votacoes() {
		Voto v1 = new Voto(Arrays.asList(livros.get(0), livros.get(1)));
		Votacao votacao1 = new Votacao(Arrays.asList(v1));
		Usuario usuario = new Usuario();
		usuario.setNome("Everton");
		usuario.setEmail("everton@provedor.com");
		votacao1.setUsuario(usuario);
		
		manager.persist(votacao1);
		
		Voto v2 = new Voto(Arrays.asList(livros.get(0), livros.get(1)));
		Votacao votacao2 = new Votacao(Arrays.asList(v2));
		Usuario usuario2 = new Usuario();
		usuario2.setNome("Jose");
		usuario2.setEmail("jose@provedor.com");
		votacao2.setUsuario(usuario2);
		
		manager.persist(votacao2);
		
		List<Votacao> votacoes = votacaoDAO.todas();
		assertEquals(2, votacoes.size());
		assertEquals(votacao1, votacoes.get(0));
		assertEquals(votacao2, votacoes.get(1));
	}
	
	private static void incluir3Livros() {
		Livro l1 = new Livro();
		l1.setTitulo("Harry Potter");
		
		Livro l2 = new Livro();
		l2.setTitulo("Effective Java");
		
		Livro l3 = new Livro();
		l3.setTitulo("Head First: Servlets & JSP");
		
		manager.persist(l1);
		manager.persist(l2);
		manager.persist(l3);
		
		livros = Arrays.asList(l1, l2, l3);
	}

}
