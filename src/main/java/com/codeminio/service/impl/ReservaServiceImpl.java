package com.codeminio.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.codeminio.dominio.Reserva;
import com.codeminio.dominio.Usuario;
import com.codeminio.dominio.Visita;
import com.codeminio.exceptions.RegraNegocioException;
import com.codeminio.repository.ReservaRepository;
import com.codeminio.repository.UsuarioRepository;
import com.codeminio.repository.VisitaRepository;
import com.codeminio.service.ReservaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaServiceImpl implements ReservaService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private VisitaRepository visitaRepository;

    @Override
    public List<Reserva> index() {
        List<Reserva> reservas = reservaRepository.findAllByData(LocalDate.now());

        return reservas;
    }

    @Override
    public void store(String username, Reserva reserva) {

        Optional<Usuario> usuario = usuarioRepository.findByLogin(username);

        List<String> errors = new ArrayList<String>();

        List<Visita> visitantesDoForm = new ArrayList<Visita>(reserva.getVisitantes());
        reserva.getVisitantes().clear();

        if (!usuario.isPresent()) {
            errors.add("Usuário inexistente");
        }

        if (reserva.getData() == null) {
            errors.add("Data inválida");
        } else if (reserva.getData().isBefore(LocalDate.now())) {
            errors.add("Você não pode reservar em uma data passada");
        }

        boolean flag = false;
        for (Visita visita : visitantesDoForm) {
            if ((visita.getNome().isEmpty() || visita.getCpf().isEmpty()) && flag == false) {
                errors.add("Por favor preencha todos os campos dos usuários");
                flag = true;
            }
        }

        boolean reservaExiste = reservaRepository.existsByDataAndNomeDaArea(reserva.getData(), reserva.getNomeDaArea());

        if (reservaExiste) {
            errors.add("Já existe uma reserva para o(a) " + reserva.getNomeDaArea() + " neste dia");
        }

        if (!errors.isEmpty()) {
            throw new RegraNegocioException(errors);
        }

        List<Visita> listaVisitantes = new ArrayList<Visita>();

        for (Visita visita : visitantesDoForm) {

            Optional<Visita> visitanteAchado = visitaRepository.findByCpf(visita.getCpf());

            if (!visitanteAchado.isPresent()) {

                visita.setAtivo(true);

                visitaRepository.save(visita);

                listaVisitantes.add(visita);

            } else {

                listaVisitantes.add(visitanteAchado.get());

            }
        }

        reserva.setVisitantes(listaVisitantes);

        reserva.setUsuario(usuario.get());

        reservaRepository.save(reserva);
    }
}
