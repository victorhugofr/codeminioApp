package com.codeminio.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeminio.exceptions.ErroAutenticacao;
import com.codeminio.exceptions.RegraNegocioException;
import com.codeminio.dominio.Morador;
import com.codeminio.dominio.Usuario;
import com.codeminio.service.impl.MoradorServiceImpl;

@RestController /* Arquitetura REST */
@RequestMapping(value = "/morador")
public class MoradorController {

	@Autowired
	private MoradorServiceImpl service;

	@PostMapping("/autenticar")
	public ResponseEntity autenticar(@RequestBody Morador morador) {
		try {
			Morador moradorAutenticado = service.autenticar(morador.getUsuario().getLogin(), morador.getUsuario().getSenha());
			return ResponseEntity.ok(moradorAutenticado);
		} catch (ErroAutenticacao e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	/* Servico RESTful */
	@GetMapping(value = "/listar", produces = "application/json")
	public ResponseEntity<List<Morador>> index() {

		List<Morador> moradores = service.listarMoradores();

		return new ResponseEntity<List<Morador>>(moradores, HttpStatus.OK);

	}

	@PostMapping("/salvar")
	public ResponseEntity cadastrar(@RequestBody Morador Morador) {
		try {
			Morador moradorSalvo = service.salvarMorador(Morador);
			return new ResponseEntity(moradorSalvo, HttpStatus.CREATED);
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Morador> procurar(@PathVariable(value = "id") Integer id) {

		Optional<Morador> morador = service.procurarPorId(id);

		return new ResponseEntity<Morador>(morador.get(), HttpStatus.OK);
	}

	@PutMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Morador> atualizar(@PathVariable(value = "id") Integer id, @RequestBody Morador morador) {

		Optional<Morador> antigoMorador = service.procurarPorId(id);

		antigoMorador.map((m) -> {
			m.getUsuario().setEmail(morador.getUsuario().getEmail());
			m.getUsuario().setNome(morador.getUsuario().getNome());
			m.getUsuario().setSenha(morador.getUsuario().getSenha());
			m.getUsuario().setLogin(morador.getUsuario().getLogin());
			m.getUsuario().setCPF(morador.getUsuario().getCPF());
			m.getUsuario().setTelefone(morador.getUsuario().getTelefone());
			m.setApartamento(morador.getApartamento());
			return service.salvarMorador(m);
		});

		return new ResponseEntity<Morador>(antigoMorador.get(), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", produces = "application/text")
	public String deletar(@PathVariable("id") Integer id) {
		service.deletarPorId(id);

		return "Morador deletado";
	}
}
