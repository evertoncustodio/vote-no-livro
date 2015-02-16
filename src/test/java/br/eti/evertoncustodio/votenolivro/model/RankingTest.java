package br.eti.evertoncustodio.votenolivro.model;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class RankingTest {

	private Ranking ranking;
	private Livro l1;
	private Livro l2;
	private Livro l3;
	
	@Before
	public void setUp() throws Exception {
		l1 = new Livro();
		l1.setTitulo("Effective Java");
		l1.setId(1L);
		Score s1 = new Score(l1, 10);
		
		l2 = new Livro();
		l2.setTitulo("Head First: Java");
		l2.setId(2L);
		Score s2 = new Score(l2, 11);
		
		l3 = new Livro();
		l3.setTitulo("Querying MS SQL Server");
		l3.setId(3L);
		Score s3 = new Score(l3, 5);
		
		ranking = new Ranking(Arrays.asList(s1, s2, s3));
	}

	@Test
	public void deve_listar_scores_em_ordem_decrescente() {
		assertEquals(3, ranking.getScores().size());
		assertEquals(11, ranking.getScores().get(0).getVotos());
		assertEquals(10, ranking.getScores().get(1).getVotos());
		assertEquals(5, ranking.getScores().get(2).getVotos());
	}

}
