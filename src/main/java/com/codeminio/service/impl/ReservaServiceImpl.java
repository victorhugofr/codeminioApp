package com.codeminio.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.codeminio.dominio.Reserva;
import com.codeminio.dominio.Usuario;
import com.codeminio.dominio.Visita;
import com.codeminio.exceptions.RegraNegocioException;
import com.codeminio.repository.UsuarioRepository;
import com.codeminio.service.ReservaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaServiceImpl implements ReservaService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void store(String username, Reserva reserva) {

        Optional<Usuario> usuario = usuarioRepository.findByLogin(username);

        List<String> errors = new ArrayList<String>();

        System.out.println(reserva.getNomeDaArea());
        System.out.println(reserva.getData());

        for (Visita visita : reserva.getVisitantes()) {
            System.out.println(visita.getNome());
            System.out.println(visita.getCpf());
        }

        if (!usuario.isPresent()) {
            errors.add("Usuário inexistente");
        }

        if (reserva.getData() == null) {
            errors.add("Data inválida");
        } else if (reserva.getData().isBefore(LocalDate.now())) {
            errors.add("Você não pode reservar em uma data passada");
        }

        boolean flag = false;
        for (Visita visita : reserva.getVisitantes()) {
            if ((visita.getNome().isEmpty() || visita.getCpf().isEmpty()) && flag == false) {
                errors.add("Por favor preencha todos os campos dos usuários");
                flag = true;
            }
        }

        if (!errors.isEmpty()) {
            throw new RegraNegocioException(errors);
        }

    }

}
