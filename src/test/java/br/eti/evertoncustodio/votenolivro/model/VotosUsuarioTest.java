package br.eti.evertoncustodio.votenolivro.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class VotosUsuarioTest {
	
	private VotosUsuario votosUsuario;

	@Before
	public void setUp() throws Exception {
		votosUsuario = new VotosUsuario();
	}

	@Test
	public void deveAdicionarVotoQuandoScoreVazio() {
		Livro livro = new Livro();
		livro.setId(1L);
		livro.setTitulo("Harry Potter");
		
		votosUsuario.adicionarVoto(livro);
		
		assertEquals(1, votosUsuario.getRanking().getScores().size());
		assertEquals(1, votosUsuario.getRanking().getScores().get(0).getVotos());
	}
	
	@Test(expected=NullPointerException.class)
	public void naoDeveAdicionarVotoQuandoLivroNulo() {
		votosUsuario.adicionarVoto(null);
	}
	
	@Test
	public void deveAdicionarVariosVotosDeUmLivro() {
		Livro livro = new Livro();
		livro.setId(1L);
		livro.setTitulo("Harry Potter");
		
		votosUsuario.adicionarVoto(livro);
		votosUsuario.adicionarVoto(livro);
		votosUsuario.adicionarVoto(livro);
		
		assertEquals(1, votosUsuario.getRanking().getScores().size());
		assertEquals(3, votosUsuario.getRanking().getScores().get(0).getVotos());
	}
	
	@Test
	public void deveAdicionarVotosDeVariosLivros() {
		Livro l1 = new Livro();
		l1.setId(1L);
		l1.setTitulo("Harry Potter");
		
		Livro l2 = new Livro();
		l2.setId(2L);
		l2.setTitulo("Harry Potter 2");
		
		Livro l3 = new Livro();
		l3.setId(3L);
		l3.setTitulo("Harry Potter 3");
		
		votosUsuario.adicionarVoto(l1);
		votosUsuario.adicionarVoto(l1);
		votosUsuario.adicionarVoto(l2);
		votosUsuario.adicionarVoto(l3);
		votosUsuario.adicionarVoto(l2);
		votosUsuario.adicionarVoto(l1);
		
		assertEquals(3, votosUsuario.getRanking().getScores().size());
		assertEquals(3, votosUsuario.getRanking().getScores().get(0).getVotos());
		assertEquals(2, votosUsuario.getRanking().getScores().get(1).getVotos());
		assertEquals(1, votosUsuario.getRanking().getScores().get(2).getVotos());
	}

}
