package br.eti.evertoncustodio.votenolivro.model;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class VotoTest {

	private Voto voto;
	private Livro l1;
	private Livro l2;
	private Livro l3;
	
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
		
		voto = new Voto(Arrays.asList(l1, l2));
	}


	@Test
	public void deve_votar_no_livro_informado() {
		voto.votarNo(l1);
		assertEquals(l1, voto.getOpcaoSelecionada());
	}
	
	@Test(expected=RuntimeException.class)
	public void nao_deve_votar_em_livro_que_nao_esta_nas_opcoes() {
		voto.votarNo(l3);
	}
	
	@Test(expected=RuntimeException.class)
	public void nao_deve_possuir_opcao_selecionada_se_ainda_nao_votou() {
		voto.getOpcaoSelecionada();
	}
	
	@Test
	public void deve_marcar_realizado_apos_votacao() {
		voto.votarNo(l1);
		assertTrue(voto.realizado());
	}
	
	@Test
	public void nao_deve_marcar_realizado_antes_de_votar() {
		assertFalse(voto.realizado());
	}
}
