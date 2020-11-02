package com.codeminio.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.codeminio.dominio.Alternativa;
import com.codeminio.dominio.Enquete;
import com.codeminio.dominio.Usuario;
import com.codeminio.dtos.EnqueteDTO;
import com.codeminio.exceptions.RegraNegocioException;
import com.codeminio.repository.AlternativaRepository;
import com.codeminio.repository.EnqueteRepository;
import com.codeminio.repository.UsuarioRepository;
import com.codeminio.service.EnqueteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnqueteServiceImpl implements EnqueteService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EnqueteRepository enqueteRepository;

    @Autowired
    private AlternativaRepository alternativaRepository;

    @Override
    public List<Enquete> index() {
        List<Enquete> enquetes = enqueteRepository
                .findByDataLimiteGreaterThanEqualOrderByDataLimiteDesc(LocalDate.now());

        return enquetes;
    }

    @Override
    public Enquete show(String username, int id) {
        Optional<Enquete> enquete = enqueteRepository.findById(id);

        List<String> errors = new ArrayList<String>();

        if (!enquete.isPresent()) {
            errors.add("Enquete inexistente");
        } else if (enquete.get().getDataLimite().isBefore(LocalDate.now())) {
            errors.add("Enquete finalizada");
        }

        if (!errors.isEmpty()) {
            throw new RegraNegocioException(errors);
        }

        return enquete.get();
    }

    @Override
    public void update(String username, int idEnquete, Integer idAlternativa) {
        Optional<Usuario> usuario = usuarioRepository.findByLogin(username);
        Optional<Enquete> enquete = enqueteRepository.findById(idEnquete);
        Optional<Alternativa> alternativa = alternativaRepository.findById(idAlternativa);

        List<String> errors = new ArrayList<String>();

        if (!usuario.isPresent()) {
            errors.add("Usuário inexistente");
        }

        if (!enquete.isPresent()) {
            errors.add("Enquete inexistente");
        }

        if (!alternativa.isPresent()) {
            errors.add("Alternativa inexistente");
        } else if (!enquete.get().getAlternativas().contains(alternativa.get())) {
            errors.add("Alternativa inválida");
        }

        boolean voted = usuarioRepository.findIfVoted(username, idEnquete) == null ? false : true;

        if (voted) {
            errors.add("Você já votou nesta enquete");
        }

        if (!errors.isEmpty()) {
            throw new RegraNegocioException(errors);
        }

        Alternativa updatedAlternativa = alternativa.get();
        updatedAlternativa.setVotantes(usuario.get());

        alternativaRepository.save(updatedAlternativa);
    }

    @Override
    public boolean checkIfVoted(String username, int idEnquete) {
        Usuario usuario = usuarioRepository.findIfVoted(username, idEnquete);

        if (usuario != null) {
            return true;
        }
        return false;
    }

    @Override
    public void store(String username, EnqueteDTO enqueteDTO) {
        Optional<Usuario> usuario = usuarioRepository.findByLogin(username);

        List<String> errors = new ArrayList<String>();

        if (!usuario.isPresent()) {
            errors.add("Usuário inexistente");
        }

        if (enqueteDTO.getTitulo().isEmpty() || enqueteDTO.getTitulo().isBlank()) {
            errors.add("Por favor preencha o título");
        }

        if (enqueteDTO.getDataLimite().isEmpty() || enqueteDTO.getTitulo().isBlank()) {
            errors.add("Por favor preencha a data limite");
        } else {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate dataLimite = LocalDate.parse(enqueteDTO.getDataLimite(), formatter);

                if (LocalDate.now().isAfter(dataLimite)) {
                    errors.add("A data não pode estar no passado");
                }
            } catch (Exception e) {
                errors.add("Por favor insira uma data válida");
            }
        }

        boolean flag = false;
        for (String alternativa : enqueteDTO.getAlternativas()) {
            if ((alternativa.isEmpty() || alternativa.isBlank()) && flag == false) {
                errors.add("Por favor preencha todas as alternativas");
                flag = true;
            }
        }

        if (!errors.isEmpty()) {
            throw new RegraNegocioException(errors);
        }

        // Transformar DTO em Entidade e salvar no banco
        Enquete enquete = new Enquete();

        enquete.setTitulo(enqueteDTO.getTitulo());
        enquete.setDataLimite(LocalDate.parse(enqueteDTO.getDataLimite(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        enquete.setCriador(usuario.get());

        enqueteRepository.save(enquete);

        for (String alternativa : enqueteDTO.getAlternativas()) {
            Alternativa a = new Alternativa();

            a.setTitulo(alternativa);
            a.setEnquete(enquete);

            alternativaRepository.save(a);
        }

    }
}
