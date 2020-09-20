package com.codeminio.controller;

import java.util.List;

import com.codeminio.dominio.Aviso;
import com.codeminio.service.AvisoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@Autowired
	AvisoService avisoService;

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/sistema/index")
	public String sistemaIndex(Model model) {

		List<Aviso> avisos = avisoService.index();

		model.addAttribute("avisos", avisos);

		return "sistema/index";

	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}
}
