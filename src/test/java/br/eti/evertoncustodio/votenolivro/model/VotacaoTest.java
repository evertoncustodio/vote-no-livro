package br.eti.evertoncustodio.votenolivro.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class VotacaoTest {

	private Votacao votacao;
	private List<Opcao> opcoes;	

	@Before
	public void setUp() throws Exception {
		opcoes = Mockito.mock(List.class);
		votacao = new Votacao(opcoes);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected=NullPointerException.class)
	public void naoDevePermitirCriarVotacaoSemOpcoes() {
		new Votacao(null);
	}
	
	@Test
	public void devePossuirProximoSeProximaPosicaoEhMenorQueONumOpcoes() {
		Mockito.when(opcoes.size()).thenReturn(10);
		assertTrue(votacao.temProximo());
	}
	
	@Test
	public void naoDevePossuirProximoSeProximaPosicaoEhIgualNumOpcoes() {
		Mockito.when(opcoes.size()).thenReturn(0);
		assertFalse(votacao.temProximo());
	}
	
	@Test(expected=IllegalStateException.class)
	public void naoDeveRetornarProximoSeAPosicaoForMaiorQueAsOpcoes() {
		Mockito.when(opcoes.size()).thenReturn(0);
		votacao.proximo();
	}
	
	@Test
	public void deveRetornarOProximoRegistroQuandoAProximaPosicaoEhMenorQueAsOpcoes() {
		List<Opcao> opcoes = listaCom3Opcoes();
		votacao = new Votacao(opcoes);
		
		assertEquals(opcoes.get(1), votacao.proximo());
	}
	
	@Test
	public void deveRetornarPosicaoAtualAposAvancar() {
		List<Opcao> opcoes = listaCom3Opcoes();
		votacao = new Votacao(opcoes);
		
		votacao.proximo();
		assertEquals(opcoes.get(1), votacao.atual());
	}

	private List<Opcao> listaCom3Opcoes() {
		Livro l1 = new Livro();
		l1.setId(1L);
		l1.setTitulo("Effective Java");
		
		Livro l2 = new Livro();
		l2.setId(2L);
		l2.setTitulo("Harry Potter");
		
		Livro l3 = new Livro();
		l3.setId(3L);
		l3.setTitulo("Head First: Servlets & JSP");
		
		Opcao op1 = new Opcao(l1, l2);
		Opcao op2 = new Opcao(l1, l3);
		Opcao op3 = new Opcao(l2, l3);
		
		return Arrays.asList(op1, op2, op3);
	}

}
