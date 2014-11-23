package br.eti.evertoncustodio.votenolivro.model.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import br.eti.evertoncustodio.votenolivro.model.Livro;
import br.eti.evertoncustodio.votenolivro.model.Opcao;
import br.eti.evertoncustodio.votenolivro.model.dao.LivroDAO;

public class OpcaoServiceImplTest {

	private OpcaoServiceImpl opcaoService;
	private LivroDAO livroDAO;

	@Before
	public void setUp() throws Exception {
		livroDAO = Mockito.mock(LivroDAO.class);
		opcaoService = new OpcaoServiceImpl();
		opcaoService.setLivroDAO(livroDAO);
	}

	@Test
	public void deveRetornar3OpcoesPara3Livros() {
		Mockito.when(livroDAO.getLista()).thenReturn(listaCom3Livros());
		List<Opcao> resultado = opcaoService.getOpcoes();
		assertEquals(3, resultado.size());
		
		Livro l1 = new Livro();
		l1.setId(1L);
		l1.setTitulo("Effective Java");
		
		Livro l2 = new Livro();
		l2.setId(2L);
		l2.setTitulo("Harry Potter");
		
		Livro l3 = new Livro();
		l3.setId(3L);
		l3.setTitulo("Head First: Servlets & JSP");
		
		Opcao[] esperado = new Opcao[]{new Opcao(l1, l2), new Opcao(l1, l3), new Opcao(l2, l3)};
		assertTrue(Arrays.equals(esperado, resultado.toArray()));
	}

	private List<Livro> listaCom3Livros() {
		Livro l1 = new Livro();
		l1.setId(1L);
		l1.setTitulo("Effective Java");
		
		Livro l2 = new Livro();
		l2.setId(2L);
		l2.setTitulo("Harry Potter");
		
		Livro l3 = new Livro();
		l3.setId(3L);
		l3.setTitulo("Head First: Servlets & JSP");
		
		return Arrays.asList(l1, l2, l3);
	}
	
	@Test
	public void deveRetornarVazioQuandoNaoHaLivros() {
		assertTrue(opcaoService.getOpcoes().isEmpty());
	}

}
