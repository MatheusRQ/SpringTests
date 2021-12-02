package br.com.mat.exemplo.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.mat.exemplo.modelo.Personagem;
import br.com.mat.exemplo.repository.AnimacaoRepository;
import br.com.mat.exemplo.repository.DubladorRepository;

//Dados que saem para o usuario -> DTO
//Dados que são recebidos pelo sistema do usuario -> Form
//Todo form é um dto, apenas tem essa diferença para organização
public class PersonagemForm {

	@NotNull @NotEmpty @Length(min = 5)
	private String nome;
	@NotNull @NotEmpty @Length(min = 5)
	private String origem;
	@NotNull @NotEmpty @Length(min = 5)
	private String dublador;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getDublador() {
		return dublador;
	}

	public void setDublador(String dublador) {
		this.dublador = dublador;
	}

	public Personagem converter(AnimacaoRepository animacaoRepository, DubladorRepository dubladorRepository) {
		return new Personagem(this.nome, animacaoRepository.findByNome(origem), dubladorRepository.findByNome(dublador));
	}

}
