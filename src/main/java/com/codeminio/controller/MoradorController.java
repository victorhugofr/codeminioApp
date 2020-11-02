package com.codeminio.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.codeminio.dominio.Documento;
import com.codeminio.dominio.Morador;
import com.codeminio.exceptions.RegraNegocioException;
import com.codeminio.helper.UsuarioHelper;
import com.codeminio.service.DocumentoService;
import com.codeminio.service.impl.MoradorServiceImpl;

@Controller /* Arquitetura REST */
@RequestMapping("/sistema/morador")
public class MoradorController {

	@Autowired
	private MoradorServiceImpl service;
	
	@Autowired
	private UsuarioHelper usuarioHelper;
	
	@Autowired
	private DocumentoService documentoService;

	@GetMapping(value = "/listar")
	public String listar(Model model) {
		List<Morador> moradores = service.listarMoradores();
		model.addAttribute("moradorLista", moradores);
		return "morador/listar";

	}

	@GetMapping(value = "/form")
	public String cadastrar(Model modelo) {
		Morador novoMorador = new Morador();
		modelo.addAttribute("morador",novoMorador);
		return "morador/cadastrar";
	}
	
	@PostMapping("/salvar")
	public ModelAndView cadastrar(Morador morador,  BindingResult br, RedirectAttributes ra) {
		ModelAndView modelAndView = null;
		try {
			service.salvarMorador(morador);
			modelAndView = new ModelAndView(new RedirectView("index", true));
		} catch (RegraNegocioException e) {
			return modelAndView;
		}
		modelAndView = new ModelAndView(new RedirectView("/sistema/index", true));
		return modelAndView;
	}

	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Morador> procurar(@PathVariable(value = "id") Integer id) {

		Optional<Morador> morador = service.procurarPorId(id);

		return new ResponseEntity<Morador>(morador.get(), HttpStatus.OK);
	}

	@PutMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Morador> atualizar(@PathVariable(value = "id") Integer id, @RequestBody Morador morador) {

		Optional<Morador> antigoMorador = service.procurarPorId(id);
//
//		antigoMorador.map((m) -> {
//			m.getUsuario().setEmail(morador.getUsuario().getEmail());
//			m.getUsuario().setNome(morador.getUsuario().getNome());
//			m.getUsuario().setSenha(morador.getUsuario().getSenha());
//			m.getUsuario().setLogin(morador.getUsuario().getLogin());
//			m.getUsuario().setCPF(morador.getUsuario().getCPF());
//			m.getUsuario().setTelefone(morador.getUsuario().getTelefone());
//			m.setApartamento(morador.getApartamento());
//			return service.salvarMorador(m);
//		});

		return new ResponseEntity<Morador>(antigoMorador.get(), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", produces = "application/text")
	public String deletar(@PathVariable("id") Integer id) {
		service.deletarPorId(id);

		return "Morador deletado";
	}
	
	@GetMapping(value = "/contas")
	public String contas(Model model) {
		String login= usuarioHelper.getUsuarioLogado().get().getLogin();
		Optional<Morador> morador = service.procurarPorLogin(login);
		List<Documento> contas = documentoService.listarContasPorMorador(morador.get().getId());
		model.addAttribute("contas", contas);
		return "morador/contas";

	}
}
