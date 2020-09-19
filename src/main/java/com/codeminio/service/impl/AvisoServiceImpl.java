package com.codeminio.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import com.codeminio.exceptions.RegraNegocioException;
import com.codeminio.dominio.Aviso;
import com.codeminio.dominio.Usuario;
import com.codeminio.repository.AvisoRepository;
import com.codeminio.repository.UsuarioRepository;
import com.codeminio.service.AvisoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvisoServiceImpl implements AvisoService {

  @Autowired
  private AvisoRepository avisoRepository;

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Override
  public List<Aviso> index() {

    List<Aviso> avisos = avisoRepository.findAll();

    avisos.sort(Comparator.comparing(Aviso::getDataCriacao).reversed());

    return avisos;

  };

  @Override
  public void store(String username, Aviso aviso) {

    Optional<Usuario> usuario = usuarioRepository.findByLogin(username);

    List<String> errors = new ArrayList<String>();

    if (!usuario.isPresent()) {
      errors.add("Usuário inexistente");
    }

    if (aviso.getTitulo().isEmpty()) {
      errors.add("O titulo do aviso não pode estar vazio");
    }

    if (aviso.getTexto().isEmpty()) {
      errors.add("O texto do aviso não pode estar vazio");
    }

    if (!errors.isEmpty()) {
      throw new RegraNegocioException(errors);
    }

    aviso.setAutor(usuario.get());

    avisoRepository.save(aviso);

  };
  
}