package br.eti.evertoncustodio.votenolivro.model.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import br.eti.evertoncustodio.votenolivro.model.Livro;
import br.eti.evertoncustodio.votenolivro.model.Ranking;
import br.eti.evertoncustodio.votenolivro.model.Score;

public class RankingServiceImplTest {
	
	private ScoreService scoreService;
	private RankingServiceImpl rankingService;
	
	@Before
	public void setUp() {
		scoreService = Mockito.mock(ScoreService.class);
		rankingService = new RankingServiceImpl();
		rankingService.setScoreService(scoreService);
	}
	
	@Test
	public void deveRetornarRankingQuandoHaApenasUmScore() {
		Mockito.when(scoreService.getLista()).thenReturn(listaComUmScore());
		
		Ranking ranking = rankingService.getRankingGeral();
		assertEquals(1, ranking.tamanho());
	}

	private List<Score> listaComUmScore() {
		return Arrays.asList(new Score[]{new Score()});
	}
	
	@Test
	public void deveRetornarRankingVazioQuandoNaoHaScore() {
		Mockito.when(scoreService.getLista()).thenReturn(listaVazia());
		
		Ranking ranking = rankingService.getRankingGeral();
		assertTrue(ranking.vazio());
	}

	private List<Score> listaVazia() {
		return Collections.EMPTY_LIST;
	}
	
	@Test
	public void deveRetornarRankingEmOrdemDecrescente() {
		Mockito.when(scoreService.getLista()).thenReturn(listaComVariosRegistros());
		
		Ranking ranking = rankingService.getRankingGeral();
		
		assertEquals(10, ranking.getScores().get(0).getVotos());
		assertEquals(8, ranking.getScores().get(1).getVotos());
		assertEquals(5, ranking.getScores().get(2).getVotos());
	}

	private List<Score> listaComVariosRegistros() {
		Score score1 = new Score(new Livro(), 10);
		Score score2 = new Score(new Livro(), 8);
		Score score3 = new Score(new Livro(), 5);
		
		return Arrays.asList(score2, score1, score3);
	}

}
