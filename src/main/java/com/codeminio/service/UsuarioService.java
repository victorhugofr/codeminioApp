package com.codeminio.service;

import com.codeminio.dominio.Usuario;

public interface UsuarioService {
	
	Usuario autenticar(String login, String senha);
	Usuario salvarUsuario(Usuario usuario);
	void validarLogin(String email);
}
