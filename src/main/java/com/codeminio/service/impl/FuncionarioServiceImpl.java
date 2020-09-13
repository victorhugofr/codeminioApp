package com.codeminio.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codeminio.exceptions.ErroAutenticacao;
import com.codeminio.exceptions.RegraNegocioException;
import com.codeminio.dominio.Funcionario;
import com.codeminio.repository.FuncionarioRepository;
import com.codeminio.repository.UsuarioRepository;
import com.codeminio.service.FuncionarioService;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private FuncionarioRepository repository;

	public FuncionarioServiceImpl(FuncionarioRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public Funcionario autenticar(String login, String senha) {
		Optional<Funcionario> funcionario = repository.findByLogin(login);

		if (!funcionario.isPresent() || !funcionario.get().getUsuario().getSenha().equals(senha)) {
			throw new ErroAutenticacao("Login ou senha inválidos");
		}

		return funcionario.get();
	}

	@Override
	@Transactional
	public void salvarFuncionario(Funcionario funcionario) {
		usuarioRepository.save(funcionario.getUsuario());
		repository.save(funcionario);
	}


	public List<Funcionario> listarFuncionarios() {
		return (List<Funcionario>) repository.findAll();
	}

	public Optional<Funcionario> procurarPorId(Integer id) {
		return repository.findById(id);
	}

	public void deletarPorId(Integer id) {
		repository.deleteById(id);
	}

}
