package com.codeminio.repository;

import java.util.List;

import com.codeminio.dominio.Recurso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecursoRepository<T extends Recurso> extends JpaRepository<T, Integer> {
    List<T> findAll();
}
