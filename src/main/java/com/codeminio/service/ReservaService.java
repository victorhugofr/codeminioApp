package com.codeminio.service;

import java.util.List;

import com.codeminio.dominio.Reserva;
import com.codeminio.dtos.ReservaDTO;

public interface ReservaService<T extends Reserva> {

    List<T> listar();

    void cadastrar(String username, ReservaDTO reserva);
}
