package br.com.mat.exemplo.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.mat.exemplo.modelo.Personagem;

//DTO - Data Transfer Object, ou VO - Value Object, uma classe feita só para usar os dados especificos que se quer usar
public class PersonagemDto {

	private Long id;
	private String nome;
	private String dublador;
	private String origem;

	public PersonagemDto(Personagem personagem) {
		this.id = personagem.getId();
		this.nome = personagem.getNome();
		this.dublador = personagem.getDublador().getNome();
		this.origem = personagem.getOrigem().getNome();
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

	public String getDublador() {
		return dublador;
	}

	public void setDublador(String dublador) {
		this.dublador = dublador;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public static List<PersonagemDto> converter(List<Personagem> lista) {
		return lista.stream().map(PersonagemDto::new).collect(Collectors.toList());
	}

}
