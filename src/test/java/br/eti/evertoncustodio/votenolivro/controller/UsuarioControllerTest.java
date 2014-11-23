package br.eti.evertoncustodio.votenolivro.controller;

import static org.junit.Assert.*;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;

import br.eti.evertoncustodio.votenolivro.model.Usuario;
import br.eti.evertoncustodio.votenolivro.model.VotosUsuario;
import br.eti.evertoncustodio.votenolivro.model.dao.UsuarioDAO;
import br.eti.evertoncustodio.votenolivro.model.dao.VotosUsuarioDAO;
import br.eti.evertoncustodio.votenolivro.model.service.RankingService;

public class UsuarioControllerTest {

	private RankingService rankingService;
	private UsuarioDAO usuarioDAO;
	private VotosUsuarioDAO votosUsuarioDAO;
	private UsuarioController usuarioController;
	private Model model;
	private HttpSession httpSession;

	@Before
	public void setUp() throws Exception {
		rankingService = Mockito.mock(RankingService.class);
		usuarioDAO = Mockito.mock(UsuarioDAO.class);
		votosUsuarioDAO = Mockito.mock(VotosUsuarioDAO.class);
		model = Mockito.mock(Model.class);
		httpSession = Mockito.mock(HttpSession.class);
		
		usuarioController = new UsuarioController();
		usuarioController.setRankingService(rankingService);
		usuarioController.setUsuarioDAO(usuarioDAO);
		usuarioController.setVotosUsuarioDAO(votosUsuarioDAO);
	}

	@Test(expected=NullPointerException.class)
	public void deveFalharSeVotosUsuarioNaoEstaNaSessao() {
		usuarioController.confirmar(model, httpSession, new Usuario());
	}
	
	@Test
	public void deveGravarVotosUsuario() {
		VotosUsuario votosUsuario = Mockito.mock(VotosUsuario.class);
		Mockito.when(httpSession.getAttribute("votosUsuario")).thenReturn(votosUsuario);
		
		assertEquals("resultado", usuarioController.confirmar(model, httpSession, new Usuario()));
		Mockito.verify(usuarioDAO).gravar(Mockito.any(Usuario.class));
		Mockito.verify(votosUsuarioDAO).gravar(Mockito.any(VotosUsuario.class));
	}

}
