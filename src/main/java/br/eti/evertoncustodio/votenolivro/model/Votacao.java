package br.eti.evertoncustodio.votenolivro.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Votacao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	@OneToMany(cascade=CascadeType.ALL)
	private List<Voto> votos = new ArrayList<Voto>();
	@OneToOne(cascade=CascadeType.ALL)
	private Usuario usuario;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar data = Calendar.getInstance();
	
	public Votacao() {
		
	}
	
	public Votacao(List<Voto> votos) {
		this.votos.addAll(votos);
	}
	
	public List<Voto> getVotos() {
		return Collections.unmodifiableList(votos);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		if(this.usuario != null) {
			throw new RuntimeException("Usuário já configurado");
		}
		
		this.usuario = usuario;
	}

	public Calendar getData() {
		return data;
	}

	public Long getId() {
		return id;
	}
	
	public boolean estaTerminada() {
		boolean terminada = true;
		
		for(Voto v: votos) {
			if(!v.realizado()) {
				terminada = false;
				break;
			}
		}
		
		return terminada;
	}
	
	public Voto atual() {
		for(Voto v: votos) {
			if(!v.realizado()) {
				return v;
			}
		}
		
		throw new RuntimeException("Votação terminada");
	}
}
