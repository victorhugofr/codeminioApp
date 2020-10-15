package com.codeminio.service;

import java.util.List;

import com.codeminio.dominio.Reserva;

public interface ReservaService {

    List<Reserva> index();

    void store(String username, Reserva reserva);

}
