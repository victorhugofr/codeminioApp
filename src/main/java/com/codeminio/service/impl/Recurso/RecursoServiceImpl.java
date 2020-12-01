package com.codeminio.service.impl.Recurso;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.codeminio.dominio.Area;
import com.codeminio.dominio.Recurso;
import com.codeminio.dominio.Usuario;
import com.codeminio.exceptions.RegraNegocioException;
import com.codeminio.repository.RecursoRepository;
import com.codeminio.repository.UsuarioRepository;
import com.codeminio.service.RecursoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class RecursoServiceImpl implements RecursoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RecursoRepository recursoRepository;

    public abstract List<Recurso> listar();

    public abstract void cadastrar(String username, String nomeDaArea);
}
