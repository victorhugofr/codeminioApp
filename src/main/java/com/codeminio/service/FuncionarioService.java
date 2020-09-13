package com.codeminio.service;

import com.codeminio.dominio.Funcionario;

public interface FuncionarioService {
	
	Funcionario autenticar(String login, String senha);
	void salvarFuncionario(Funcionario usuario);
}
