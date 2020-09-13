package com.codeminio.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.codeminio.dominio.Aviso;

@Repository
public interface AvisoRepository extends GenericRepository<Aviso> {

  List<Aviso> findAll();

  Optional<Aviso> findById(Long id);

}