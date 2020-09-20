package com.codeminio.service;

import com.codeminio.dominio.Morador;

public interface MoradorService {
	
	Morador autenticar(String login, String senha);
	void salvarMorador(Morador usuario);
	void validarLogin(String email);
	void validarCPF(String email);
}
