package com.codeminio.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.codeminio.dominio.Area;
import com.codeminio.dominio.Usuario;
import com.codeminio.exceptions.RegraNegocioException;
import com.codeminio.repository.AreaRepository;
import com.codeminio.repository.UsuarioRepository;
import com.codeminio.service.AreaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AreaServiceImpl implements AreaService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AreaRepository areaRepository;

    @Override
    public void cadastrarArea(String username, String nomeDaArea) {
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
            areaRepository.save(area);
        } catch (Exception e) {
            errors.add("Área já cadastrada");
            throw new RegraNegocioException(errors);
        }

    }
}
