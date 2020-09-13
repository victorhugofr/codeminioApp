package com.codeminio.codeminio.security;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.codeminio.dominio.Usuario;
import com.codeminio.repository.UsuarioRepository;

@Service
@Transactional
public class ImplementacaoUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuario = usuarioRepository.findByLogin(username);
		if(!usuario.isPresent()) {
			throw new UsernameNotFoundException("Usuário não foi encontrado");
		}
		return new User(usuario.get().getLogin(),
				usuario.get().getPassword(),
				true,true,true,true,usuario.get().getAuthorities());
	}

}
