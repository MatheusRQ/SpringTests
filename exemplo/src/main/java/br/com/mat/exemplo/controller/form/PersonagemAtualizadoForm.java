package br.com.mat.exemplo.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.mat.exemplo.modelo.Personagem;
import br.com.mat.exemplo.repository.DubladorRepository;
import br.com.mat.exemplo.repository.PersonagemRepository;

public class PersonagemAtualizadoForm {

	@NotNull @NotEmpty @Length(min = 5)
	private String nome;
//	@NotNull @NotEmpty @Length(min = 5)
//	private String origem; //para testes, a origem não sera atualizavel
	@NotNull @NotEmpty @Length(min = 5)
	private String dublador;
	private String descricao;

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Personagem atualizar(Long id, PersonagemRepository personagemRepository, DubladorRepository dubladorRepository) {
		Personagem personagem = personagemRepository.findById(id).get();
		personagem.setNome(nome);
		personagem.setDublador(dubladorRepository.findByNome(dublador));
		personagem.setDescricao(descricao);
		return personagem;
	}

	

}
