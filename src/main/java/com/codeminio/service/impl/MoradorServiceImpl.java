package com.codeminio.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codeminio.dominio.Morador;
import com.codeminio.dominio.Role;
import com.codeminio.exceptions.ErroAutenticacao;
import com.codeminio.exceptions.RegraNegocioException;
import com.codeminio.repository.MoradorRepository;
import com.codeminio.repository.UsuarioRepository;
import com.codeminio.service.MoradorService;

@Service
public class MoradorServiceImpl implements MoradorService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private MoradorRepository repository;

	public MoradorServiceImpl(MoradorRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public Morador autenticar(String login, String senha) {
		Optional<Morador> morador = repository.findByLogin(login);

		if (!morador.isPresent() || !morador.get().getUsuario().getSenha().equals(senha)) {
			throw new ErroAutenticacao("Usuário ou senha inválidos");
		}

		return morador.get();
	}

	@Override
	@Transactional
	public void salvarMorador(Morador morador) {
		BCryptPasswordEncoder  encoder = new BCryptPasswordEncoder ();
		morador.getUsuario().setSenha(encoder.encode(morador.getUsuario().getSenha()));
		List<Role> roles = new ArrayList<Role>();
		roles.add(new Role(2));
		morador.getUsuario().setRoles(roles);
		validarApartamento(morador.getApartamento());
		validarNome(morador.getUsuario().getNome());
		validarLogin(morador.getUsuario().getLogin());
		validarCPF(morador.getUsuario().getCPF());
		usuarioRepository.save(morador.getUsuario());
		repository.save(morador);
	}

	private void validarApartamento(String apartamento) {
		if (apartamento == null)
			throw new RegraNegocioException("Apartamento é obrigatorio");
	}

	private void validarNome(String nome) {
		if (nome == null) {
			throw new RegraNegocioException("Nome é obrigatorio");
		}

	}

	@Override
	public void validarLogin(String login) {
		if (login == null) {
			throw new RegraNegocioException("Login é obrigatorio");
		}
		Optional<Morador> moradores = procurarPorLogin(login);
		if (moradores.isPresent()) {
			throw new RegraNegocioException("Já existe um morador cadastrado com este Login.");
		}
	}

	@Override
	public void validarCPF(String cpf) {
		if (cpf == null) {
			throw new RegraNegocioException("CPF é obrigatorio");
		}
		Optional<Morador> moradores = procurarPorCPF(cpf);
		if (moradores.isPresent()) {
			throw new RegraNegocioException("Já existe um morador cadastrado com este CPF.");
		}
	}

	public List<Morador> listarMoradores() {
		return (List<Morador>) repository.findAll();
	}

	public Optional<Morador> procurarPorId(Integer id) {
		return repository.findById(id);
	}

	public Optional<Morador> procurarPorLogin(String login) {
		return repository.findByLogin(login);
	}

	public Optional<Morador> procurarPorCPF(String cpf) {
		return repository.findByCPF(cpf);
	}

	public void deletarPorId(Integer id) {
		repository.deleteById(id);
	}
}
