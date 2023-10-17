package br.seisboth.prova.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.seisboth.prova.excecao.ColaboradorNotFoundException;
import br.seisboth.prova.modelo.Colaborador;
import br.seisboth.prova.repositorio.ColaboradorRepositorio;


@Service
public class ColaboradorServico {

	@Autowired
	private ColaboradorRepositorio colaboradorRepositorio;
	
	public Colaborador criarColaborador(Colaborador colaborador) {
		return colaboradorRepositorio.save(colaborador);
	}

	public Colaborador buscarColaboradorPorId(Long id) throws ColaboradorNotFoundException {
		Optional<Colaborador> opt = colaboradorRepositorio.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			throw new ColaboradorNotFoundException("Colaborador com id : " + id + " não existe");
		}
	}
	
	public List<Colaborador> buscarColaboradorsPorNome(String nome) {
		return colaboradorRepositorio.findByNomeContainingIgnoreCase(nome);
	}

	public List<Colaborador> buscarTodosColaboradors() {
		return colaboradorRepositorio.findAll();
	}

	public Colaborador alterarColaborador(Colaborador colaborador) {
		return colaboradorRepositorio.save(colaborador);
	}

	public void apagarColaborador(Long id) throws ColaboradorNotFoundException {
		Colaborador colaborador = buscarColaboradorPorId(id);
		colaboradorRepositorio.delete(colaborador);
	}
}
