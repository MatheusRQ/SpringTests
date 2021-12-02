package br.com.mat.exemplo.controller.form;

import br.com.mat.exemplo.modelo.Personagem;
import br.com.mat.exemplo.repository.AnimacaoRepository;
import br.com.mat.exemplo.repository.DubladorRepository;

//Dados que saem para o usuario -> DTO
//Dados que são recebidos pelo sistema do usuario -> Form
//Todo form é um dto, apenas tem essa diferença para organização
public class PersonagemForm {

	private String nome;
	private String origem;
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
