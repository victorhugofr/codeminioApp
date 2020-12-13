package com.codeminio.service.impl;

import java.util.List;

import com.codeminio.dominio.Recurso;
import com.codeminio.dominio.Reserva;
import com.codeminio.dtos.ReservaDTO;
import com.codeminio.repository.ReservaRepository;
import com.codeminio.service.ReservaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class ReservaServiceImpl<T extends Reserva, U extends Recurso> implements ReservaService<T, U> {

    @Autowired
    private ReservaRepository<T, U> reservaRepository;

    @Override
    public List<T> listar() {
        List<T> reservas = reservaRepository.findAll();

        return reservas;
    }

    @Override
    public abstract void cadastrar(String username, ReservaDTO reserva);
}
