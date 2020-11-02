package com.codeminio.service;

import java.util.List;
import java.util.Optional;

import com.codeminio.dominio.Morador;

public interface MoradorService {
	
	Morador autenticar(String login, String senha);
	void salvarMorador(Morador usuario);
	void validarLogin(String email);
	void validarCPF(String email);
	public List<Morador> listarMoradores();
	public Optional<Morador> procurarPorId(Integer id);
	public Optional<Morador> procurarPorLogin(String login);
	public Optional<Morador> procurarPorCPF(String cpf);
	public void deletarPorId(Integer id);
}
