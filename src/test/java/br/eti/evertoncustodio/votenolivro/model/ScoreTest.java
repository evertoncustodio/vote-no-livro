package br.eti.evertoncustodio.votenolivro.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ScoreTest {

	private Score score;

	@Before
	public void setUp() throws Exception {
		score = new Score();
		Livro livro = new Livro();
		livro.setTitulo("Effective Java");
	}

	@Test
	public void deveAdicionarUmVoto() {
		score.adicionarVoto();
		
		assertEquals(1, score.getVotos());
	}
	
	@Test
	public void deveComecarSemVotos() {
		assertEquals(0, score.getVotos());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoDeveCriarComVotosNegativos() {
		Score score = new Score(new Livro(), -10);
	}

}
