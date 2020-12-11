package com.codeminio.service.impl;

import java.util.List;

import com.codeminio.dominio.Recurso;
import com.codeminio.dtos.RecursoDTO;
import com.codeminio.repository.RecursoRepository;
import com.codeminio.service.RecursoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class RecursoServiceImpl<T extends Recurso> implements RecursoService<T> {

    @Autowired
    private RecursoRepository<T> recursoRepository;

    @Override
    public List<T> listar() {
        return recursoRepository.findAll();
    }

    public abstract void cadastrar(String username, RecursoDTO recursoDTO);
}
