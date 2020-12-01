package com.codeminio.service.impl;

import java.time.LocalDate;
import java.util.List;

import com.codeminio.dominio.Reserva;
import com.codeminio.repository.ReservaRepository;
import com.codeminio.service.ReservaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class ReservaServiceImpl implements ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Override
    public List<Reserva> index() {
        List<Reserva> reservas = reservaRepository.findAllByData(LocalDate.now());

        return reservas;
    }

    @Override
    public abstract void store(String username, Reserva reserva);
}
