package br.eti.evertoncustodio.votenolivro.model.service;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.eti.evertoncustodio.votenolivro.model.Livro;
import br.eti.evertoncustodio.votenolivro.model.Ranking;
import br.eti.evertoncustodio.votenolivro.model.Usuario;
import br.eti.evertoncustodio.votenolivro.model.Votacao;
import br.eti.evertoncustodio.votenolivro.model.Voto;
import br.eti.evertoncustodio.votenolivro.model.dao.VotacaoDAO;

public class CriadorDeRankingTest {

	private CriadorDeRanking criador;
	private VotacaoDAO votacaoDAO;
	
	@Before
	public void setUp() throws Exception {
		votacaoDAO = mock(VotacaoDAO.class);
		criador = new CriadorDeRanking();
		criador.setVotacoes(votacaoDAO);
	}

	@Test
	public void deve_criar_ranking_com_todas_as_votacoes() {
		when(votacaoDAO.todas()).thenReturn(duasVotacoesDeUsuariosDiferentes());
		
		Ranking ranking = criador.geral();
		verify(votacaoDAO).todas();
		assertEquals(3, ranking.getScores().size());
		assertEquals(2, ranking.getScores().get(0).getVotos());
	}
	
	@Test
	public void deve_criar_ranking_com_votacoes_do_usuario() {
		Usuario usuario = new Usuario();
		usuario.setNome("Everton");
		usuario.setEmail("everton@provedor.com");
		usuario.setId(1L);
		
		when(votacaoDAO.votacoesDo(usuario)).thenReturn(duasVotacoesDoMesmoUsuario(usuario));
		
		Ranking ranking = criador.por(usuario);
		verify(votacaoDAO).votacoesDo(usuario);
		assertEquals(3, ranking.getScores().size());
		assertEquals(2, ranking.getScores().get(0).getVotos());
	}
	
	private List<Votacao> duasVotacoesDoMesmoUsuario(Usuario usuario) {
		Livro l1 = new Livro();
		l1.setTitulo("Effective Java");
		l1.setId(1L);
		
		Livro l2 = new Livro();
		l2.setTitulo("Head First: Java");
		l2.setId(2L);
		
		Livro l3 = new Livro();
		l3.setTitulo("Querying MS SQL Server");
		l3.setId(3L);
		
		Voto v1 = new Voto(Arrays.asList(l1, l2));
		v1.setId(1L);
		Voto v2 = new Voto(Arrays.asList(l2, l3));
		v2.setId(2L);
		
		Votacao votacao1 = new Votacao(Arrays.asList(v1, v2));
		votacao1.setUsuario(usuario);
		votacao1.atual().votarNo(l1);
		votacao1.atual().votarNo(l2);
		
		Voto v3 = new Voto(Arrays.asList(l1, l2));
		v1.setId(1L);
		Voto v4 = new Voto(Arrays.asList(l2, l3));
		v2.setId(2L);
		
		Votacao votacao2 = new Votacao(Arrays.asList(v3, v4));
		votacao2.setUsuario(usuario);
		votacao2.atual().votarNo(l1);
		votacao2.atual().votarNo(l3);
		
		return Arrays.asList(votacao1, votacao2);
	}
	
	private List<Votacao> duasVotacoesDeUsuariosDiferentes() {
		Livro l1 = new Livro();
		l1.setTitulo("Effective Java");
		l1.setId(1L);
		
		Livro l2 = new Livro();
		l2.setTitulo("Head First: Java");
		l2.setId(2L);
		
		Livro l3 = new Livro();
		l3.setTitulo("Querying MS SQL Server");
		l3.setId(3L);
		
		Voto v1 = new Voto(Arrays.asList(l1, l2));
		v1.setId(1L);
		Voto v2 = new Voto(Arrays.asList(l2, l3));
		v2.setId(2L);
		
		Votacao votacao1 = new Votacao(Arrays.asList(v1, v2));
		votacao1.atual().votarNo(l1);
		votacao1.atual().votarNo(l2);
		Usuario usuario = new Usuario();
		usuario.setNome("Everton");
		usuario.setEmail("everton@provedor.com");
		usuario.setId(1L);
		votacao1.setUsuario(usuario);
		
		
		Voto v3 = new Voto(Arrays.asList(l1, l2));
		v1.setId(1L);
		Voto v4 = new Voto(Arrays.asList(l2, l3));
		v2.setId(2L);
		
		Votacao votacao2 = new Votacao(Arrays.asList(v3, v4));
		votacao2.atual().votarNo(l1);
		votacao2.atual().votarNo(l3);
		Usuario usuario2 = new Usuario();
		usuario2.setNome("Jose");
		usuario2.setEmail("jose@provedor.com");
		usuario2.setId(2L);
		votacao2.setUsuario(usuario2);
		
		return Arrays.asList(votacao1, votacao2);
	}

}
