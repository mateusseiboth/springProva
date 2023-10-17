package br.seisboth.prova.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.seisboth.prova.excecao.ColaboradorNotFoundException;
import br.seisboth.prova.modelo.Colaborador;
import br.seisboth.prova.servico.ColaboradorServico;
import jakarta.validation.Valid;

@Controller
public class ColaboradorControle {

	@Autowired
	private ColaboradorServico colaboradorServico;

	
	@GetMapping("/")
	public String listarColaboradors(Model model) {
		List<Colaborador> colaboradores = colaboradorServico.buscarTodosColaboradors();
		model.addAttribute("itens", colaboradores);
		return "/lista-colaboradores";
	}

	@PostMapping("/buscar")
	public String buscarColaboradors(Model model, @Param("nome") String nome) {
		if (nome == null) {
			return "redirect:/";
		}
		List<Colaborador> colaboradores = colaboradorServico.buscarColaboradorsPorNome(nome);
		model.addAttribute("itens", colaboradores);
		return "/lista-colaboradores";
	}

	@GetMapping("/novo")
	public String novoColaborador(Model model) {
		model.addAttribute("item", new Colaborador());
		return "/novo-colaborador";
	}

	@PostMapping("/gravar")
	public String gravarColaborador(@ModelAttribute("item") @Valid Colaborador colaborador, BindingResult result,
			RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return "/novo-colaborador";
		}
		colaboradorServico.criarColaborador(colaborador);
		attributes.addFlashAttribute("mensagem", "Gravado com sucesso");
		return "redirect:/novo";
	}

	@GetMapping("/editar/{id}")
	public String formEditar(@PathVariable("id") long id, Model model) {
		try {
			Colaborador colaborador = colaboradorServico.buscarColaboradorPorId(id);
			model.addAttribute("item", colaborador);
		} catch (ColaboradorNotFoundException e) {
			System.out.println(e.getMessage());
			return "redirect:/";
		}
		return "/alterar-colaborador";
	}

	@PostMapping("/editar/{id}")
	public String editarColaborador(@PathVariable("id") long id, @ModelAttribute("item") @Valid Colaborador colaborador,
			BindingResult result) {
		if (result.hasErrors()) {
			colaborador.setId(id);
			return "/alterar-colaborador";
		}
		colaboradorServico.alterarColaborador(colaborador);
		return "redirect:/";
	}

	@GetMapping("/apagar/{id}")
	public String apagarColaborador(@PathVariable("id") long id, RedirectAttributes attributes) {
		try {
			colaboradorServico.apagarColaborador(id);
		} catch (ColaboradorNotFoundException e) {
			attributes.addFlashAttribute("mensagem", e.getMessage());
		}
		return "redirect:/";
	}
}
