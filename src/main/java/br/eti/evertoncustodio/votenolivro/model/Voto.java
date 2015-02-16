package br.eti.evertoncustodio.votenolivro.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Voto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	@ManyToMany
	private List<Livro> opcoes = new ArrayList<Livro>();
	@ManyToOne
	@JoinColumn(name="opcaoSelecionada")
	private Livro opcaoSelecionada;
	
	public Voto() {
		
	}
	
	public Voto(List<Livro> opcoes) {
		this.opcoes.addAll(opcoes);
	}
	
	public List<Livro> getOpcoes() {
		return Collections.unmodifiableList(opcoes);
	}
	
	public Livro getOpcaoSelecionada() {
		if(!realizado()) {
			throw new RuntimeException("A votação ainda não foi realizada");
		}
		
		return opcaoSelecionada;
	}
	
	public boolean realizado() {
		return opcaoSelecionada != null;
	}
	
	public void votarNo(Livro livro) {
		if(!opcoes.contains(livro)) {
			throw new RuntimeException("O livro selecionado não está entre as opções de votação");
		}
		
		opcaoSelecionada = livro;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
