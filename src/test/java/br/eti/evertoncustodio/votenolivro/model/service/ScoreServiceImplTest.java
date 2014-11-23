package br.eti.evertoncustodio.votenolivro.model.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import br.eti.evertoncustodio.votenolivro.model.Livro;
import br.eti.evertoncustodio.votenolivro.model.dao.LivroDAO;
import br.eti.evertoncustodio.votenolivro.model.dao.ScoreDAO;

public class ScoreServiceImplTest {
	
	private ScoreServiceImpl scoreService;
	private ScoreDAO scoreDAO;
	private LivroDAO livroDAO;

	@Before
	public void setUp() throws Exception {
		scoreDAO = Mockito.mock(ScoreDAO.class);
		livroDAO = Mockito.mock(LivroDAO.class);
		
		scoreService = new ScoreServiceImpl();
		scoreService.setScoreDAO(scoreDAO);
		scoreService.setLivroDAO(livroDAO);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected=NullPointerException.class)
	public void naoDeveVotarComLivroNulo() {
		scoreService.votar(null);
	}
	
	@Test
	public void deveVotarComIdLivroPreenchido() {
		Mockito.when(livroDAO.getLivro(Mockito.anyLong())).thenReturn(umLivro());
		scoreService.votar(10L);
		Mockito.verify(scoreDAO).votar(Mockito.any(Livro.class));
	}
	
	private Livro umLivro() {
		Livro l1 = new Livro();
		l1.setId(10L);
		l1.setTitulo("Harry Potter");
		
		return l1;
	}
}
