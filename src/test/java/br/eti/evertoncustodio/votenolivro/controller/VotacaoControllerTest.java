package br.eti.evertoncustodio.votenolivro.controller;

import static org.junit.Assert.assertEquals;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;

import br.eti.evertoncustodio.votenolivro.model.Opcao;
import br.eti.evertoncustodio.votenolivro.model.Votacao;
import br.eti.evertoncustodio.votenolivro.model.VotosUsuario;
import br.eti.evertoncustodio.votenolivro.model.dao.LivroDAO;
import br.eti.evertoncustodio.votenolivro.model.service.ScoreService;

public class VotacaoControllerTest {

	private ScoreService scoreService;
	private LivroDAO livroDAO;
	private Model model;
	private HttpSession session;
	private Votacao votacao;
	private VotosUsuario votosUsuario;
	private Opcao opcao;
	
	private VotacaoController votacaoController;

	@Before
	public void setUp() throws Exception {
		scoreService = Mockito.mock(ScoreService.class);
		livroDAO = Mockito.mock(LivroDAO.class);
		model = Mockito.mock(Model.class);
		session = Mockito.mock(HttpSession.class);
		votacao = Mockito.mock(Votacao.class);
		votosUsuario = Mockito.mock(VotosUsuario.class);
		opcao = Mockito.mock(Opcao.class);
		
		votacaoController = new VotacaoController();
		votacaoController.setLivroDAO(livroDAO);
		votacaoController.setScoreService(scoreService);
	}
	
	@Test(expected=NullPointerException.class)
	public void deveFalharSeVotosUsuarioNaoEstaNaSessao() {
		votacaoController.votar(model, session, 1L);
	}
	
	@Test
	public void deveRetornarOpcoesQuandoVotacaoNaoTerminou() {
		Mockito.when(session.getAttribute("votacao")).thenReturn(votacao);
		Mockito.when(session.getAttribute("votosUsuario")).thenReturn(votosUsuario);
		Mockito.when(votacao.temProximo()).thenReturn(true);
		Mockito.when(votacao.proximo()).thenReturn(opcao);
		
		assertEquals("opcoes", votacaoController.votar(model, session, 1L));
	}
	
	@Test
	public void deveRetornarUsuarioQuandoVotacaoTerminar() {
		Mockito.when(session.getAttribute("votacao")).thenReturn(votacao);
		Mockito.when(session.getAttribute("votosUsuario")).thenReturn(votosUsuario);
		Mockito.when(votacao.temProximo()).thenReturn(false);
		
		assertEquals("usuario", votacaoController.votar(model, session, 1L));
	}

}
