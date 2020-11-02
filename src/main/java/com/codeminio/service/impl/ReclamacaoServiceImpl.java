package com.codeminio.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import com.codeminio.exceptions.RegraNegocioException;
import com.codeminio.dominio.Reclamacao;
import com.codeminio.dominio.Usuario;
import com.codeminio.repository.ReclamacaoRepository;
import com.codeminio.repository.UsuarioRepository;
import com.codeminio.service.ReclamacaoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReclamacaoServiceImpl implements ReclamacaoService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ReclamacaoRepository reclamacaoRepository;
	
	@Override
	public List<Reclamacao> index() {

	    List<Reclamacao> reclamacoes = reclamacaoRepository.findAll();

	    reclamacoes.sort(Comparator.comparing(Reclamacao::getDataCriacao).reversed());

	    return reclamacoes;

	  };

	  @Override
	  public void store(String username, Reclamacao reclamacao) {

		  Optional<Usuario> usuario = usuarioRepository.findByLogin(username);

		  List<String> errors = new ArrayList<String>();

		  if (!usuario.isPresent()) {
			  errors.add("Usuário inexistente");
		  }

		  if (reclamacao.getTitulo().isEmpty()) {
			  errors.add("O motivo da reclamação não pode estar vazio");
		  }

		  if (reclamacao.getTexto().isEmpty()) {
			  errors.add("A descrição do problema não pode estar vazia");
		  }

		  if (!errors.isEmpty()) {
			  throw new RegraNegocioException(errors);
		  }

		  reclamacao.setMorador(usuario.get());

		  reclamacaoRepository.save(reclamacao);

	  };
	
}
