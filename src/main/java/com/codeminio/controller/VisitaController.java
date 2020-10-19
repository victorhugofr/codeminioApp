package com.codeminio.controller;
import java.security.Principal;
import java.util.List;

import com.codeminio.dominio.Visita;
import com.codeminio.exceptions.RegraNegocioException;
import com.codeminio.service.VisitaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sistema/visita")
public class VisitaController {

	@Autowired
	private VisitaService visitaService;

	@GetMapping(value = "/create")
	public String create(Model model) {

	    Visita visita = new Visita();
	    model.addAttribute("visita", visita);
	
	    return "morador/visitantes";

	}
	
	@GetMapping(value = "/portaria")
	public String index(Model model) {

		// Lista de visitas
		List<Visita> visitas = visitaService.index();
	    model.addAttribute("visitasLista", visitas);
	
	    return "funcionario/portaria";

	}

	@PostMapping
	public String store(Principal principal, Model model, Visita visita) {

    try {

      String username = principal.getName();

      visitaService.store(username, visita);

      return "redirect:index";

    } catch (RegraNegocioException e) {

      List<String> errors = e.getErrorList();
      model.addAttribute("errors", errors);

      return "morador/visitantes";

    }

  }

	
}
