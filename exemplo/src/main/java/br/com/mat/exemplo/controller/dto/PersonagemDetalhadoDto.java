package br.com.mat.exemplo.controller.dto;

import br.com.mat.exemplo.modelo.Personagem;

public class PersonagemDetalhadoDto {

	private Long id;
	private String nome;
	private String dublador;
	private String origem;
	private String descricao;

	public PersonagemDetalhadoDto(Personagem personagem) {
		this.id = personagem.getId();
		this.nome = personagem.getNome();
		this.dublador = personagem.getDublador().getNome();
		this.origem = personagem.getOrigem().getNome();
		this.descricao = personagem.getDescricao();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getDublador() {
		return dublador;
	}

	public String getOrigem() {
		return origem;
	}

	public String getDescricao() {
		return descricao;
	}

}
