package br.com.mat.exemplo.controller;

import java.net.URI;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.mat.exemplo.controller.dto.PersonagemDetalhadoDto;
import br.com.mat.exemplo.controller.dto.PersonagemDto;
import br.com.mat.exemplo.controller.form.PersonagemAtualizadoForm;
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
	
//	@GetMapping
////	@RequestMapping(value="/animacoes", method = RequestMethod.GET) -> Forma alternativa de fazer o "@GetMapping"
////	@ResponseBody -> A anotação "RestController" implicita a utilização dessa anotação em todos os métodos.
//	public List<PersonagemDto> lista(String nomeDublador) {
////		Animacao animacao = new Animacao();
////		animacao.setNome("Animacao Teste");
////		animacao.setAnoLancamento(2013);
////		
////		Dublador dublador = new Dublador();
////		dublador.setNome("Dublador Teste");
////		
////		Personagem personagem = new Personagem();
////		personagem.setNome("Personagem teste");
////		personagem.setDublador(dublador);
////		personagem.setOrigem(animacao);
//		
//		List<Personagem> personagens;
//		
//		if(nomeDublador == null) {
//			personagens = personagemRepository.findAll();
//			
//		} else {
//			personagens = personagemRepository.findByDubladorNome(nomeDublador);
//		}
//		return PersonagemDto.converter(personagens);
//	}
	@GetMapping @Cacheable(value = "personagemLista")
//	public Page<PersonagemDto> lista(@RequestParam(required = false) String nomeDublador, 
//			@RequestParam int paginas, @RequestParam int qtd, @RequestParam String ordenacao) {
	public Page<PersonagemDto> lista(@RequestParam(required = false) String nomeDublador, 
			Pageable paginacao) {
		
		Page<Personagem> personagens;
		
		if(nomeDublador == null) {
			personagens = personagemRepository.findAll(paginacao);
			
		} else {
			personagens = personagemRepository.findByDubladorNome(nomeDublador, paginacao);
		}
		return PersonagemDto.converter(personagens);
	}
	
	@PostMapping @Transactional @CacheEvict(value = "personagemLista", allEntries = true)
	public ResponseEntity<PersonagemDto> cadastro(@RequestBody @Valid PersonagemForm form, UriComponentsBuilder uriBuilder) {
		Personagem personagem = form.converter(animacaoRepository, dubladorRepository);
		personagemRepository.save(personagem);
		
		URI uri = uriBuilder.path("/animacoes/{id}").buildAndExpand(personagem.getId()).toUri();
		return ResponseEntity.created(uri).body(new PersonagemDto(personagem));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PersonagemDetalhadoDto> detalhar(@PathVariable Long id) {
		// metodo getOne antigo:
		// Topico topico = topicoRepository.getOne(id);

		// Utilizar agora o metodo getById:
//		Topico topico = topicoRepository.getById(id);
		
		Optional<Personagem> optional = personagemRepository.findById(id);
		if(optional.isPresent()) {
			return ResponseEntity.ok(new PersonagemDetalhadoDto(optional.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}") @Transactional
	public ResponseEntity<PersonagemDetalhadoDto> atualizacao(@PathVariable Long id, @RequestBody @Valid PersonagemAtualizadoForm form) {
		Optional<Personagem> optional = personagemRepository.findById(id);
		if(optional.isPresent()) {
			Personagem personagem = form.atualizar(id, personagemRepository, dubladorRepository);
			return ResponseEntity.ok(new PersonagemDetalhadoDto(personagem));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}") @Transactional
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		Optional<Personagem> optional = personagemRepository.findById(id);
		if(optional.isPresent()) {
			personagemRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
