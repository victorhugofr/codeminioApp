package com.codeminio.service;

import java.util.List;

import com.codeminio.dominio.Recurso;

public interface RecursoService {
    public List<Recurso> listar();

    public void cadastrar(String username, String nomeDaArea);
}