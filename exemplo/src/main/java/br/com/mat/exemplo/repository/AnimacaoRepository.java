package br.com.mat.exemplo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mat.exemplo.modelo.Animacao;
import br.com.mat.exemplo.modelo.Dublador;

public interface AnimacaoRepository extends JpaRepository<Animacao, Long> {

	Animacao findByNome(String origem);

}
