package com.codeminio.helper;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.codeminio.dominio.Usuario;
import com.codeminio.service.UsuarioService;

@Component
public class UsuarioHelper {
	
	@Autowired
	private UsuarioService usuarioService;
	
	public Optional<Usuario> getUsuarioLogado() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User usuario = (User)principal;
		return usuarioService.procurarPorLogin(usuario.getUsername());
	}
}
