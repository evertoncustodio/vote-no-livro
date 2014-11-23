package br.eti.evertoncustodio.votenolivro.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Score implements Serializable, Comparable<Score>{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	@OneToOne
	private Livro livro;
	private int votos;
	
	public Score() {
		
	}
	
	public Score(Livro livro, int votos) {
		if(votos < 0) {
			throw new IllegalArgumentException("votos não pode ser negativo");
		}
		
		this.livro = livro;
		this.votos = votos;
	}

	public int getVotos() {
		return votos;
	}
	
	public Livro getLivro() {
		return livro;
	}

	public int compareTo(Score o) {
		return o.votos - this.votos;
	}
	
	public void adicionarVoto() {
		votos++;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Score other = (Score) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
