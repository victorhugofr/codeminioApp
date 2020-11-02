package com.codeminio.controller;

import com.codeminio.dominio.Classificados;
import com.codeminio.exceptions.RegraNegocioException;
import com.codeminio.service.ClassificadosService;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sistema/classificados")
public class ClassificadosController {
	
	@Autowired
	private ClassificadosService classificadosService;
	
	@GetMapping(value = "/create")
	public String create(Model model) {

		Classificados classificado = new Classificados();
		model.addAttribute("classificado", classificado);

		return "classificados/ofertarProduto";

	}
	
	@GetMapping(value = "/lista")
	public String index(Model model) {

		 // Lista de visitas
		List<Classificados> classificados = classificadosService.index();
		model.addAttribute("classificadosLista", classificados);
		
		return "classificados/produtos";

	}
	
	@PostMapping
	public String store(Principal principal, Model model, Classificados classificados) {

		try {

			String username = principal.getName();

			classificadosService.store(username, classificados);

			return "redirect:index";

		} catch (RegraNegocioException e) {

			List<String> errors = e.getErrorList();
			model.addAttribute("errors", errors);
			model.addAttribute("classificado", classificados);

			return "classificados/ofertarProduto";

		}

	}
	
	

}
