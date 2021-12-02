package br.com.mat.exemplo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.mat.exemplo.controller.dto.PersonagemDto;
import br.com.mat.exemplo.controller.form.PersonagemForm;
import br.com.mat.exemplo.modelo.Personagem;
import br.com.mat.exemplo.repository.AnimacaoRepository;
import br.com.mat.exemplo.repository.DubladorRepository;
import br.com.mat.exemplo.repository.PersonagemRepository;

@RestController
@RequestMapping("/animacoes")
public class AnimacaoController {

	@Autowired
	private PersonagemRepository personagemRepository;
	@Autowired
	private AnimacaoRepository animacaoRepository;
	@Autowired
	private DubladorRepository dubladorRepository;
	
	@GetMapping
//	@RequestMapping(value="/animacoes", method = RequestMethod.GET) -> Forma alternativa de fazer o "@GetMapping"
//	@ResponseBody -> A anotação "RestController" implicita a utilização dessa anotação em todos os métodos.
	public List<PersonagemDto> lista(String nomeDublador) {
//		Animacao animacao = new Animacao();
//		animacao.setNome("Animacao Teste");
//		animacao.setAnoLancamento(2013);
//		
//		Dublador dublador = new Dublador();
//		dublador.setNome("Dublador Teste");
//		
//		Personagem personagem = new Personagem();
//		personagem.setNome("Personagem teste");
//		personagem.setDublador(dublador);
//		personagem.setOrigem(animacao);
		
		List<Personagem> personagens;
		
		if(nomeDublador == null) {
			personagens = personagemRepository.findAll();
			
		} else {
			personagens = personagemRepository.findByDubladorNome(nomeDublador);
		}
		return PersonagemDto.converter(personagens);
	}
	
	@PostMapping
	public void cadastro(@RequestBody PersonagemForm form) {
		Personagem personagem = form.converter(animacaoRepository, dubladorRepository);
		personagemRepository.save(personagem);
	}
}
