package com.codeminio.service;

import java.util.List;

import com.codeminio.dominio.Aviso;

public interface AvisoService {

  List<Aviso> index();

  void store(String username, Aviso aviso);

}