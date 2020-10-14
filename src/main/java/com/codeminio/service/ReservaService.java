package com.codeminio.service;

import com.codeminio.dominio.Reserva;

public interface ReservaService {
    void store(String username, Reserva reserva);   
}
