package com.codeminio.service;

import java.util.List;

import com.codeminio.dominio.Aviso;

public interface AvisoService {
  List<Aviso> index();

  Aviso show(Integer idAviso);

  List<Aviso> index(Integer idFuncionario);

  Aviso show(Integer idFuncionario, Integer idAviso);

  Aviso store(Integer idFuncionario, Aviso aviso);

  Aviso update(Integer idFuncionario, Integer idAviso, Aviso aviso);

  Aviso delete(Integer idFuncionario, Integer idAviso);

}