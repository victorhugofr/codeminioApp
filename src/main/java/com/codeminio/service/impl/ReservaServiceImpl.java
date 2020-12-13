package com.codeminio.service.impl;

import java.time.LocalDate;
import java.util.List;

import com.codeminio.dominio.Reserva;
import com.codeminio.dtos.ReservaDTO;
import com.codeminio.repository.ReservaRepository;
import com.codeminio.service.ReservaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class ReservaServiceImpl<T extends Reserva> implements ReservaService<T> {

    @Autowired
    private ReservaRepository<T> reservaRepository;

    @Override
    public List<T> listar() {
        List<T> reservas = reservaRepository.findAllByData(LocalDate.now());

        return reservas;
    }

    @Override
    public abstract void cadastrar(String username, ReservaDTO reserva);
}
