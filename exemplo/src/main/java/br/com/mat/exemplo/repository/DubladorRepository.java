package br.com.mat.exemplo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mat.exemplo.modelo.Animacao;
import br.com.mat.exemplo.modelo.Dublador;

public interface DubladorRepository extends JpaRepository<Dublador, Long> {

	Dublador findByNome(String dublador);

}
