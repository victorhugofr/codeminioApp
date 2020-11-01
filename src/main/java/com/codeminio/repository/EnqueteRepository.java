package com.codeminio.repository;

import java.time.LocalDate;
import java.util.List;

import com.codeminio.dominio.Enquete;

import org.springframework.stereotype.Repository;

@Repository
public interface EnqueteRepository extends GenericRepository<Enquete> {
    List<Enquete> findByDataLimiteGreaterThanEqualOrderByDataLimiteDesc(LocalDate dataLimite);
}
