package com.codeminio.service;

import java.util.List;

import com.codeminio.dominio.Recurso;
import com.codeminio.dtos.RecursoDTO;

public interface RecursoService<T extends Recurso> {
    public List<T> listar();

    public void cadastrar(String username, RecursoDTO recursoDTO);
}