package com.codeminio.service;

import java.util.List;

import com.codeminio.dominio.Recurso;

public interface RecursoService<T extends Recurso> {
    public List<T> listar();

    public void cadastrar(String username, String nomeDaArea);
}