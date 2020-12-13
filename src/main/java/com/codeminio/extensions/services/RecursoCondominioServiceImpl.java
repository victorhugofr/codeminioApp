package com.codeminio.extensions.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.codeminio.dominio.Usuario;
import com.codeminio.dtos.RecursoDTO;
import com.codeminio.exceptions.RegraNegocioException;
import com.codeminio.extensions.dtos.RecursoCondominioDTO;
import com.codeminio.extensions.models.Area;
import com.codeminio.repository.RecursoRepository;
import com.codeminio.repository.UsuarioRepository;
import com.codeminio.service.impl.RecursoServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecursoCondominioServiceImpl extends RecursoServiceImpl<Area> {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RecursoRepository<Area> recursoRepository;

    @Override
    public void cadastrar(String username, RecursoDTO recursoDTO) {
        RecursoCondominioDTO recursoCondominioDTO = (RecursoCondominioDTO) recursoDTO;

        String nomeDaArea = recursoCondominioDTO.getNomeDaArea();

        Optional<Usuario> usuario = usuarioRepository.findByLogin(username);

        List<String> errors = new ArrayList<String>();

        if (!usuario.isPresent()) {
            errors.add("Usuário inexistente");
        }

        if (nomeDaArea.isEmpty() || nomeDaArea.trim().isEmpty()) {
            errors.add("O nome da área não pode estar vazio");
        }

        if (!errors.isEmpty()) {
            throw new RegraNegocioException(errors);
        }

        Area area = new Area();
        area.setNomeDaArea(nomeDaArea);

        try {
            recursoRepository.save(area);
        } catch (Exception e) {
            errors.add("Área já cadastrada");
            throw new RegraNegocioException(errors);
        }
    }
}
