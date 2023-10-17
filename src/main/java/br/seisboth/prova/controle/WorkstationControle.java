package br.seisboth.prova.controle;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.seisboth.prova.excecao.ColaboradorNotFoundException;
import br.seisboth.prova.modelo.Workstation;
import br.seisboth.prova.modelo.Colaborador;
import br.seisboth.prova.servico.WorkstationServico;
import br.seisboth.prova.servico.ColaboradorServico;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/workstation")
public class WorkstationControle {

	@Autowired
	private ColaboradorServico colaboradorServico;

	@Autowired
	private WorkstationServico workstationServico;

	
	@GetMapping("/buscar-workstation/{id}")
	public String novoWorkstation(@PathVariable("id") long id, Model model) {
		String pagina = "";
		try {
			Colaborador colaborador = colaboradorServico.buscarColaboradorPorId(id);
			if (colaborador.getWorkstation() == null) {
				Workstation workstation = new Workstation();
				workstation.setColaborador(colaborador);
				model.addAttribute("item", workstation);
				pagina = "/nova-workstation";
			} else {
				model.addAttribute("item", colaborador.getWorkstation());
				pagina = "/alterar-workstation";
			}

		} catch (ColaboradorNotFoundException e) {
			System.out.println(e.getMessage());
			return "redirect:/";
		}
		return pagina;
	}

	@PostMapping("/gravar-workstation/{idColaborador}")
	public String gravarWorkstation(@PathVariable("idColaborador") long idColaborador,
			@ModelAttribute("item") @Valid Workstation workstation, BindingResult result, RedirectAttributes attributes) {
		try {
			Colaborador colaborador = colaboradorServico.buscarColaboradorPorId(idColaborador);
			workstation.setColaborador(colaborador);
		} catch (ColaboradorNotFoundException e) {
			e.printStackTrace();
		}

		if (result.hasErrors()) {
			return "/nova-workstation";
		}
		workstationServico.criarWorkstation(workstation);
		attributes.addFlashAttribute("mensagem", "Gravado com sucesso");
		return "redirect:/";
	}

	@PostMapping("/alterar-workstation/{idColaborador}/{idWorkstation}")
	public String alterarWorkstation(@PathVariable("idColaborador") long idColaborador,
			@PathVariable("idWorkstation") long idWorkstation, @ModelAttribute("item") @Valid Workstation workstation,
			BindingResult result, RedirectAttributes attributes) {
		try {
			Colaborador colaborador = colaboradorServico.buscarColaboradorPorId(idColaborador);
			workstation.setColaborador(colaborador);
			workstation.setId(idWorkstation);			
		} catch (ColaboradorNotFoundException e) {
			e.printStackTrace();
		}

		if (result.hasErrors()) {			
			return "/alterar-workstation";
		}
		workstationServico.alterarWorkstation(workstation);
		attributes.addFlashAttribute("mensagem", "Gravado com sucesso");
		return "redirect:/";
	}
	
}
