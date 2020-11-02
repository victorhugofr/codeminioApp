package com.codeminio.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import com.codeminio.exceptions.RegraNegocioException;
import com.codeminio.dominio.Classificados;
import com.codeminio.dominio.Usuario;
import com.codeminio.repository.ClassificadosRepository;
import com.codeminio.repository.UsuarioRepository;
import com.codeminio.service.ClassificadosService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassificadosServiceImpl implements ClassificadosService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ClassificadosRepository classificadosRepository;

	@Override
	public List<Classificados> index() {
		List<Classificados> classificados = classificadosRepository.findAll();

		classificados.sort(Comparator.comparing(Classificados::getDataCriacao).reversed());

	    return classificados;
	}

	@Override
	public void store(String username, Classificados classificados) {
		Optional<Usuario> usuario = usuarioRepository.findByLogin(username);

		List<String> errors = new ArrayList<String>();

		if (!usuario.isPresent()) {
			errors.add("Usuário inexistente");
		}
		if (classificados.getApartamento().isEmpty()) {
			errors.add("O apartamento não pode estar vazio");
		}
		if (classificados.getTelefone().isEmpty()) {
			errors.add("O telefone não pode estar vazio");
		}
		if (classificados.getPreco().isEmpty()) {
			errors.add("O preço não pode estar vazio");
		}
		if (classificados.getTexto().isEmpty()) {
			errors.add("O campo 'Informações' deve ser preenchido");
		}
		if (!errors.isEmpty()) {
			throw new RegraNegocioException(errors);
		}
	
		classificados.setLocador(usuario.get());
	
		classificadosRepository.save(classificados);
		
	}

}
