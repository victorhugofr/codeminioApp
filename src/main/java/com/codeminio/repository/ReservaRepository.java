package com.codeminio.repository;

import java.time.LocalDate;
import java.util.List;

import com.codeminio.dominio.Reserva;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository<T extends Reserva> extends JpaRepository<T, Integer> {

    List<T> findAllByData(LocalDate data);

    boolean existsByDataAndRecurso(LocalDate data, int idRecurso);
}
