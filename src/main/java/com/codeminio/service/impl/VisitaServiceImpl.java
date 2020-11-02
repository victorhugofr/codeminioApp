package com.codeminio.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.codeminio.exceptions.RegraNegocioException;
import com.codeminio.dominio.Visita;
import com.codeminio.dominio.Usuario;
import com.codeminio.repository.VisitaRepository;
import com.codeminio.repository.UsuarioRepository;
import com.codeminio.service.VisitaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitaServiceImpl implements VisitaService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private VisitaRepository visitaRepository;
	
	@Override
	public List<Visita> index(){
		
		List<Visita> visitas = visitaRepository.findAll();
		
		return visitas;
	}
	
	@Override
	public void store(String username, Visita visita) {
		
		Optional<Usuario> usuario = usuarioRepository.findByLogin(username);
		
		List<String> errors = new ArrayList<String>();
		
		if(!usuario.isPresent()) {
			errors.add("Usuário não existe");
		}
		
		if(visita.getNome().isEmpty()) {
			errors.add("O nome não pode estar vazio");
		}
		
		if(visita.getCpf().isEmpty()) {
			errors.add("O CPF não pode estar vazio");
		}
		
		if (!errors.isEmpty()) {
			throw new RegraNegocioException(errors);
		}
		
		visita.setAtivo(true);
		
		visitaRepository.save(visita);
		
	}

}
