package br.com.mat.exemplo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mat.exemplo.modelo.Personagem;

public interface PersonagemRepository extends JpaRepository<Personagem, Long> {

	List<Personagem> findByNome(String nome);

	//O uso do underline é para especificar que é um relacionamento
	//É usado em casos de um relacionamento ter o mesmo nome de um atributo da classe
	//Exemplo: A classe tem um atributo chamado nomeCurso e um relacionamento a Curso que tem um atributo "Nome"
	//Tirando esses casos, underline é opcional
	//List<Personagem> findByDublador_Nome(String nomeDublador);
//	List<Personagem> findByDubladorNome(String nomeDublador);
	Page<Personagem> findByDubladorNome(String nomeDublador, Pageable paginacao);
	
	
	//Exemplo de query fora dessa normatização
	/*
	 @Query("SELECT t FROM Topico t WHERE t.curso.nome = :nomeCurso")
     List<Topico> carregarPorNomeDoCurso(@Param("nomeCurso")(String nomeCurso);
     */

}
