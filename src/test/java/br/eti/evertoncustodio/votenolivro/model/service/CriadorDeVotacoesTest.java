package br.eti.evertoncustodio.votenolivro.model.service;


import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.eti.evertoncustodio.votenolivro.model.Livro;
import br.eti.evertoncustodio.votenolivro.model.Votacao;
import br.eti.evertoncustodio.votenolivro.model.dao.LivroDAO;

public class CriadorDeVotacoesTest {

	private CriadorDeVotacoes criador;
	private LivroDAO livroDAO;
	
	@Before
	public void setUp() throws Exception {
		livroDAO = mock(LivroDAO.class);
		criador = new CriadorDeVotacoes();
		criador.setLivros(livroDAO);
		
		when(livroDAO.todos()).thenReturn(tresLivros());
	}

	@Test
	public void deve_criar_nova_votacao_em_branco() {
		Votacao votacao = criador.nova();
		assertNull(votacao.getUsuario());
		assertFalse(votacao.estaTerminada());
	}
	
	private List<Livro> tresLivros() {
		Livro l1 = new Livro();
		l1.setTitulo("Effective Java");
		l1.setId(1L);
		
		Livro l2 = new Livro();
		l2.setTitulo("Head First: Java");
		l2.setId(2L);
		
		Livro l3 = new Livro();
		l3.setTitulo("Querying MS SQL Server 2014");
		l3.setId(3L);
		
		return Arrays.asList(l1, l2, l3);
	}
}
