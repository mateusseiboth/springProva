package br.seisboth.prova.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.seisboth.prova.modelo.Colaborador;

public interface ColaboradorRepositorio extends JpaRepository<Colaborador, Long> {
	List<Colaborador> findByNomeContainingIgnoreCase(String nome);

}
