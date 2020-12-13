package com.codeminio.repository;

import java.time.LocalDate;
import java.util.List;

import com.codeminio.dominio.Recurso;
import com.codeminio.dominio.Reserva;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository<T extends Reserva, U extends Recurso> extends JpaRepository<T, Integer> {

    List<T> findAll();

    boolean existsByDataAndRecurso(LocalDate data, U recurso);
}
