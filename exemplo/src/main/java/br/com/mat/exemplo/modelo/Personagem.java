package br.com.mat.exemplo.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Personagem {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	@ManyToOne
	private Dublador dublador;
	@ManyToOne
	private Animacao origem;
	
	public Personagem() {}

	public Personagem(String nome, Animacao origem, Dublador dublador) {
		this.nome = nome;
		this.dublador = dublador;
		this.origem = origem;
	}

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

	public Dublador getDublador() {
		return dublador;
	}

	public void setDublador(Dublador dublador) {
		this.dublador = dublador;
	}

	public Animacao getOrigem() {
		return origem;
	}

	public void setOrigem(Animacao origem) {
		this.origem = origem;
	}

}
