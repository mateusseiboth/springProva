package br.seisboth.prova.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.seisboth.prova.modelo.Workstation;
import br.seisboth.prova.repositorio.WorkstationRepositorio;


@Service
public class WorkstationServico {

	@Autowired
	private WorkstationRepositorio workstationRepositorio;
	
	public Workstation criarWorkstation(Workstation estudante) {
		return workstationRepositorio.save(estudante);
	}
	
	public Workstation alterarWorkstation(Workstation estudante) {
		return workstationRepositorio.save(estudante);
	}
}
