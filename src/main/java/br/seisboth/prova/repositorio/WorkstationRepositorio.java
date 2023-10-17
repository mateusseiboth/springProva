package br.seisboth.prova.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.seisboth.prova.modelo.Workstation;

public interface WorkstationRepositorio extends JpaRepository<Workstation, Long> {
}
