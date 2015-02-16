package br.eti.evertoncustodio.votenolivro.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class VotacaoTest {

	private Votacao votacao;
	private Livro l1;
	private Livro l2;
	private Livro l3;
	
	private Voto v1;
	private Voto v2;
	
	@Before
	public void setUp() {
		l1 = new Livro();
		l1.setTitulo("Effective Java");
		l1.setId(1L);
		
		l2 = new Livro();
		l2.setTitulo("Head First: Java");
		l2.setId(2L);
		
		l3 = new Livro();
		l3.setTitulo("Querying MS SQL Server");
		l3.setId(3L);
		
		v1 = new Voto(Arrays.asList(l1, l2));
		v1.setId(1L);
		v2 = new Voto(Arrays.asList(l2, l3));
		v2.setId(2L);
		votacao = new Votacao(Arrays.asList(v1, v2));
	}
	
	@Test(expected=RuntimeException.class)
	public void nao_deve_permitir_alterar_usuario() {
		Usuario usuario = new Usuario();
		usuario.setNome("Everton");
		usuario.setEmail("everton@provedor.com");
		votacao.setUsuario(usuario);
		
		Usuario usuario2 = new Usuario();
		usuario2.setNome("José");
		usuario2.setEmail("jose@provedor.com");
		votacao.setUsuario(usuario2);
	}
	
	@Test
	public void deve_marcar_terminada_quando_todos_os_votos_foram_realizados() {
		votacao.getVotos().get(0).votarNo(l1);
		votacao.getVotos().get(1).votarNo(l2);
		assertTrue(votacao.estaTerminada());
	}
	
	@Test
	public void nao_deve_marcar_terminada_quando_houver_votos_nao_realizados() {
		votacao.getVotos().get(0).votarNo(l1);
		assertFalse(votacao.estaTerminada());
	}
	
	@Test
	public void deve_alterar_votacao_atual_apos_votar() {
		assertEquals(v1, votacao.atual());
		votacao.atual().votarNo(l1);
		assertEquals(v2, votacao.atual());
	}

}
