package com.codeminio.service;

import java.util.Optional;

import com.codeminio.dominio.Usuario;

public interface UsuarioService {
	
	Usuario autenticar(String login, String senha);
	Usuario salvarUsuario(Usuario usuario);
	void validarLogin(String email);
	Optional<Usuario> procurarPorLogin(String login);
}
