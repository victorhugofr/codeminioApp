package com.codeminio.service.impl;

import java.util.List;
import java.util.Optional;

import com.codeminio.exceptions.RegraNegocioException;
import com.codeminio.dominio.Aviso;
import com.codeminio.dominio.Funcionario;
import com.codeminio.dominio.Usuario;
import com.codeminio.repository.AvisoRepository;
import com.codeminio.repository.FuncionarioRepository;
import com.codeminio.repository.UsuarioRepository;
import com.codeminio.service.AvisoService;
import com.jayway.jsonpath.Option;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvisoServiceImpl implements AvisoService {

  @Autowired
  private AvisoRepository avisoRepository;

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Override
  public void store(String username, Aviso aviso) {

    Optional<Usuario> usuario = usuarioRepository.findByLogin(username);

    if (usuario.isPresent() && !(aviso.getTexto().isEmpty())){
      System.out.println("OK");

      aviso.setAutor(usuario.get());

      try {
        avisoRepository.save(aviso);
        
      } catch (Exception e) {
        System.out.println(e);
      }

    } else {
      System.out.println("NOT OK");
    }

    System.out.println(usuario.toString());

    // Optional<Funcionario> funcionario = funcionarioRepository.findById(idFuncionario);

    // return funcionario.map(f -> {

    //   aviso.setAutor(f);

    //   return avisoRepository.save(aviso);

    // }).orElseThrow(() -> new RegraNegocioException("Funcionario " + idFuncionario + " não encontrado"));

  };

  // @Override
  // public List<Aviso> index() {
  //   List<Aviso> avisos = avisoRepository.findAll();

  //   return avisos;
  // };

  // @Override
  // public Aviso show(Integer idAviso) {
  //   Optional<Aviso> aviso = avisoRepository.findById(idAviso);

  //   return aviso.orElseThrow(() -> new RegraNegocioException("Aviso " + idAviso + " não encontrado"));
  // }

  // @Override
  // public List<Aviso> index(Integer idFuncionario) {
  //   Optional<Funcionario> funcionario = funcionarioRepository.findById(idFuncionario);

  //   return funcionario.map(f -> {
  //     return f.getAvisos();
  //   }).orElseThrow(() -> new RegraNegocioException("Funcionario " + idFuncionario + " não encontrado"));
  // }

  // @Override
  // public Aviso show(Integer idFuncionario, Integer idAviso) {
  //   Optional<Funcionario> funcionario = funcionarioRepository.findById(idFuncionario);

  //   return funcionario.map(f -> {

  //     Aviso aviso = f.getAviso(idAviso);

  //     if (aviso == null) {
  //       throw new RegraNegocioException("Aviso " + idAviso + " não encontrado");
  //     }

  //     return aviso;

  //   }).orElseThrow(() -> new RegraNegocioException("Funcionario " + idFuncionario + " não encontrado"));

  // }

  // @Override
  // public Aviso update(Integer idFuncionario, Integer idAviso, Aviso aviso) {
  //   Optional<Funcionario> funcionario = funcionarioRepository.findById(idFuncionario);

  //   return funcionario.map(f -> {
  //     Aviso novoAviso = f.getAviso(idAviso);

  //     if (novoAviso == null) {
  //       throw new RegraNegocioException("Aviso " + idAviso + " não encontrado");
  //     }

  //     novoAviso.setTexto(aviso.getTexto());

  //     avisoRepository.save(novoAviso);

  //     return novoAviso;

  //   }).orElseThrow(() -> new RegraNegocioException("Funcionario " + idFuncionario + " não encontrado"));

  // };

  // @Override
  // public Aviso delete(Integer idFuncionario, Integer idAviso) {
  //   Optional<Funcionario> funcionario = funcionarioRepository.findById(idFuncionario);

  //   return funcionario.map(f -> {
  //     Aviso aviso = f.getAviso(idAviso);

  //     if (aviso == null) {
  //       throw new RegraNegocioException("Aviso " + idAviso + " não encontrado");
  //     }

  //     avisoRepository.delete(aviso);

  //     return aviso;

  //   }).orElseThrow(() -> new RegraNegocioException("Funcionario " + idFuncionario + " não encontrado"));
  // };
}