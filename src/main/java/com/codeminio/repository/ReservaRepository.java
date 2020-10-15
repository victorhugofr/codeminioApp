package com.codeminio.repository;

import java.time.LocalDate;
import java.util.List;

import com.codeminio.dominio.Reserva;

import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends GenericRepository<Reserva> {

    List<Reserva> findAllByData(LocalDate data);
    
    boolean existsByDataAndNomeDaArea(LocalDate data, String nomeDaArea);

}
