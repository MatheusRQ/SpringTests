package br.com.mat.exemplo.modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Dublador {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	@OneToMany(mappedBy = "dublador")
	private List<Personagem> personagens;
//	@ManyToMany
//	private List<Animacao> animacoes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Personagem> getPersonagens() {
		return personagens;
	}

	public void setPersonagens(List<Personagem> personagens) {
		this.personagens = personagens;
	}

//	public List<Animacao> getAnimacoes() {
//		return animacoes;
//	}
//
//	public void setAnimacoes(List<Animacao> animacoes) {
//		this.animacoes = animacoes;
//	}

}
